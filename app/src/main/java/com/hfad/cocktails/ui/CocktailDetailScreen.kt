package com.hfad.cocktails.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.cocktails.viewmodel.CocktailViewModel
import com.hfad.cocktails.model.Cocktail
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailDetailScreen(
    cocktailId: Int,
    cocktailViewModel: CocktailViewModel = viewModel()
) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val cocktailState = produceState(initialValue = null as Cocktail?, key1 = cocktailId) {
        value = cocktailViewModel.getCocktailById(cocktailId)
    }
    val cocktail = cocktailState.value

    if (cocktail == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ładowanie...")
        }
    } else {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val scrollState = rememberScrollState()

        // Scaffold = CollapsingToolbar + FAB
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                LargeTopAppBar(
                    title = { Text(cocktail.name) },
                    navigationIcon = {
                        IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Wróć")
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    actions = {
                        // jakieś dodatkowe akcje?
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    elevation = FloatingActionButtonDefaults.elevation(12.dp),
                    onClick = {
                        val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = "smsto:".toUri() // brak numeru – wyświetli wybór kontaktu
                            putExtra("sms_body", cocktail.ingredients)
                        }
                        context.startActivity(smsIntent)
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Wyślij składniki SMS")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Collapsing obrazek na górze!
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(10.dp, RoundedCornerShape(24.dp))
                ) {
                    Image(
                        painter = painterResource(id = cocktail.detailImageRes.takeIf { it != 0 } ?: cocktail.imageRes),
                        contentDescription = cocktail.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(24.dp))
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                // reszta szczegółów
                Column(Modifier.padding(20.dp)) {
                    Text(text = "Składniki: ${cocktail.ingredients}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(8.dp))
                    Text(text = "Sposób przygotowania: ${cocktail.instructions}", style = MaterialTheme.typography.bodyLarge)

                    if (cocktail.notes.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Uwagi: ${cocktail.notes}", style = MaterialTheme.typography.bodyMedium)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    TimerScreen(defaultTime = cocktail.defaultTime)
                }
            }
        }
    }
}
