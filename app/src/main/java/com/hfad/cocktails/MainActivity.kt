package com.hfad.cocktails

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hfad.cocktails.model.Cocktail
import com.hfad.cocktails.ui.CocktailListScreen
import com.hfad.cocktails.ui.MasterDetailScreen
import com.hfad.cocktails.ui.theme.CocktailsTheme
import com.hfad.cocktails.ui.CategoryFilterDropdown
import com.hfad.cocktails.ui.HomeTabsScreen
import com.hfad.cocktails.viewmodel.CocktailViewModel
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailsTheme {
                MyCocktailApp()
            }
        }
    }
}

@Composable
fun MyCocktailApp(cocktailViewModel: CocktailViewModel = viewModel()) {
    val cocktails by cocktailViewModel.cocktails.collectAsState(initial = emptyList())
    val configuration = LocalConfiguration.current
    val isMasterDetail = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ||
            configuration.screenWidthDp >= 600
    val context = LocalContext.current

    if (isMasterDetail) {
        MasterDetailScreen(
            cocktails = cocktails,
            onCocktailClick = { /* tablet */ }
        )
    } else {
        HomeTabsScreen(
            cocktails = cocktails,
            onCocktailClick = { cocktail ->
                context.startActivity(
                    Intent(context, DetailActivity::class.java).apply {
                        putExtra("cocktailId", cocktail.id)
                    }
                )
            }
        )
    }
}

