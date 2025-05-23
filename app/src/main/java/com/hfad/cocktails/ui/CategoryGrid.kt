package com.hfad.cocktails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hfad.cocktails.model.Cocktail

@Composable
fun CategoryGrid(
    cocktails: List<Cocktail>,
    onCocktailClick: (Cocktail) -> Unit
) {
    val categories = listOf("Wszystkie", "Rum", "Gin", "Tequila", "Bezalkoholowe")
    var selectedCategory by remember { mutableStateOf("Wszystkie") }

    // Filtrowanie kategorii/składniku:
    val filteredCocktails = if (selectedCategory == "Wszystkie") {
        cocktails
    } else {
        cocktails.filter {
            it.category.contains(selectedCategory, ignoreCase = true) ||
                    it.ingredients.contains(selectedCategory, ignoreCase = true)
        }
    }

    Column {
        CategoryFilterDropdown(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(filteredCocktails) { cocktail ->
                Card(
                    modifier = Modifier
                        .padding(12.dp)
                        .aspectRatio(1f)
                        .fillMaxWidth()
                        .shadow(12.dp, RoundedCornerShape(28.dp))
                        .clickable { onCocktailClick(cocktail) },
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF4F3FF)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = cocktail.imageRes),
                            contentDescription = cocktail.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(72.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            cocktail.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF7C83FD)
                        )
                    }
                }
            }
        }
    }
}
