package com.hfad.cocktails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hfad.cocktails.model.Cocktail

@Composable
fun CategoryGrid(
    cocktails: List<Cocktail>,
    onCocktailClick: (Cocktail) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(cocktails) { cocktail ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { onCocktailClick(cocktail) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = cocktail.imageRes),
                        contentDescription = cocktail.name,
                        modifier = Modifier.size(96.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(cocktail.name)
                }
            }
        }
    }
}
