package com.hfad.cocktails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.cocktails.model.Cocktail
import com.hfad.cocktails.viewmodel.CocktailViewModel

@Composable
fun CocktailDetailScreen(
    cocktailId: Int,
    cocktailViewModel: CocktailViewModel = viewModel()
) {
    val cocktailState = produceState(initialValue = null as Cocktail?, key1 = cocktailId) {
        value = cocktailViewModel.getCocktailById(cocktailId)
    }
    val cocktail = cocktailState.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (cocktail == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ładowanie...")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = cocktail.imageRes),
                    contentDescription = cocktail.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = cocktail.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(text = "Składniki: ${cocktail.ingredients}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Sposób przygotowania: ${cocktail.instructions}", style = MaterialTheme.typography.bodyLarge)

                if (cocktail.notes.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Uwagi: ${cocktail.notes}", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                TimerScreen(defaultTime = cocktail.defaultTime)
            }

            FloatingActionButton(
                onClick = { /* tutaj kod do wysłania SMS z ingredients */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Wyślij składniki SMS")            }
        }
    }
}
