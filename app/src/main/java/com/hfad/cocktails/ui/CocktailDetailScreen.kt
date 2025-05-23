package com.hfad.cocktails.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.material.icons.rounded.Send
import com.hfad.cocktails.model.Cocktail
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
            topBar = {
                LargeTopAppBar(
                    title = { Text(cocktail.name) },
                    navigationIcon = {
                        IconButton(onClick = { /* back */ }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Wróć")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            cocktailViewModel.updateFavoriteStatus(
                                cocktail.id,
                                !cocktail.isFavorite // przełącz na przeciwny stan
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = if (cocktail.isFavorite) "Usuń z ulubionych" else "Dodaj do ulubionych",
                                tint = if (cocktail.isFavorite) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }
                        IconButton(onClick = { /* TODO: udostępnij */ }) {
                            Icon(Icons.Filled.Share, contentDescription = "Udostępnij")
                        }
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(16.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(24.dp))
                ) {
                    Image(
                        painter = painterResource(id = cocktail.detailImageRes.takeIf { it != 0 }
                            ?: cocktail.imageRes),
                        contentDescription = cocktail.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                // reszta szczegółów
                Column(Modifier.padding(20.dp)) {
                    Text(
                        text = cocktail.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Składniki:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = cocktail.ingredients,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Sposób przygotowania:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        text = cocktail.instructions,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                TimerScreen(defaultTime = cocktail.defaultTime)
            }
        }
    }
}
