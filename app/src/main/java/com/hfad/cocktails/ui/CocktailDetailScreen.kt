package com.hfad.cocktails.ui

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.cocktails.viewmodel.CocktailViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailDetailScreen(
    cocktailId: Int,
    cocktailViewModel: CocktailViewModel = viewModel()
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val cocktailFlow = cocktailViewModel.getCocktailFlowById(cocktailId)
    val cocktail by cocktailFlow.collectAsState(initial = null)

    when (val c = cocktail) {
        null -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ładowanie...")
        }
        else -> {
/*            val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            val scrollState = rememberScrollState()*/
            Scaffold(
                topBar = {
                    LargeTopAppBar(
                        title = { Text(c.name) },
                        navigationIcon = {
                            IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Wróć")
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                cocktailViewModel.updateFavoriteStatus(
                                    c.id,
                                    !c.isFavorite
                                )
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = if (c.isFavorite) "Usuń z ulubionych" else "Dodaj do ulubionych",
                                    tint = if (c.isFavorite) MaterialTheme.colorScheme.primary else Color.Gray
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
                            painter = painterResource(id = c.detailImageRes.takeIf { it != 0 } ?: c.imageRes),
                            contentDescription = c.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(Modifier.padding(20.dp)) {
                        Text(
                            text = c.name,
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
                            text = c.ingredients,
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
                            text = c.instructions,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TimerScreen(defaultTime = c.defaultTime)
                }
            }
        }
    }
}
