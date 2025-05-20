package com.hfad.cocktails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hfad.cocktails.ui.CocktailDetailScreen
import com.hfad.cocktails.ui.theme.CocktailsTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailsTheme {
                val cocktailId = intent.getIntExtra("cocktailId", 0)
                CocktailDetailScreen(cocktailId = cocktailId)
            }
        }
    }
}
