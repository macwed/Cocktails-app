package com.hfad.cocktails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.cocktails.model.Cocktail

@Composable
fun CocktailListScreen(
    cocktails: List<Cocktail>,
    onCocktailClick: (Cocktail) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cocktails) { cocktail ->
            CocktailListItem(cocktail = cocktail, onCocktailClick = onCocktailClick)
        }
    }
}

@Composable
fun CocktailListItem(
    cocktail: Cocktail,
    onCocktailClick: (Cocktail) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onCocktailClick(cocktail) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = cocktail.imageRes),
                contentDescription = cocktail.name,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = cocktail.name, style = MaterialTheme.typography.titleMedium)
                // inne pola?
                Text(text = cocktail.category, style = MaterialTheme.typography.bodySmall)
            }        }
    }
}

