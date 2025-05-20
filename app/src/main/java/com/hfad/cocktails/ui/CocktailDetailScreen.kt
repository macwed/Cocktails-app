package com.hfad.cocktails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.cocktails.viewmodel.CocktailViewModel

@Composable
fun CocktailDetailScreen(
    cocktailId: Int,
    cocktailViewModel: CocktailViewModel = viewModel()
) {
    val cocktailState = produceState(initialValue = null as com.hfad.cocktails.model.Cocktail?, key1 = cocktailId) {
        value = cocktailViewModel.getCocktailById(cocktailId)
    }
    val cocktail = cocktailState.value

    if (cocktail == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ładowanie...")
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cocktail.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Składniki: ${cocktail.ingredients}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Sposób przygotowania: ${cocktail.instructions}", style = MaterialTheme.typography.bodyLarge)

            if (cocktail.notes.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Uwagi: ${cocktail.notes}", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Timer
            TimerScreen(defaultTime = cocktail.defaultTime)
        }
    }
}
