package com.hfad.cocktails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hfad.cocktails.model.Cocktail

@Composable
fun MasterDetailScreen(
    cocktails: List<Cocktail>,
    onCocktailClick: (Cocktail) -> Unit
) {
    var selectedCocktailId by rememberSaveable { mutableStateOf<Int?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            CocktailListScreen(
                cocktails = cocktails,
                onCocktailClick = { cocktail ->
                    selectedCocktailId = cocktail.id
                    onCocktailClick(cocktail)
                }
            )
        }
        Box(modifier = Modifier.weight(2f).padding(16.dp)) {
            if (selectedCocktailId != null) {
                CocktailDetailScreen(cocktailId = selectedCocktailId!!)
            } else {
                Text("Wybierz koktajl z listy", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
