package com.hfad.cocktails.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hfad.cocktails.model.Cocktail
import kotlinx.coroutines.launch

@Composable
fun HomeTabsScreen(
    cocktails: List<Cocktail>,
    onCocktailClick: (Cocktail) -> Unit
) {
    val tabs = listOf("Start", "Alkoholowe", "Bezalkoholowe")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    text = { Text(title) }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> StartTab()
                1 -> CategoryGrid(
                    cocktails = cocktails.filter { it.category == "Alkoholowe" },
                    onCocktailClick = onCocktailClick
                )
                2 -> CategoryGrid(
                    cocktails = cocktails.filter { it.category == "Bezalkoholowe" },
                    onCocktailClick = onCocktailClick
                )
            }
        }
    }
}

@Composable
fun StartTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Witamy w aplikacji barmańskiej!\nWybierz zakładkę by przeglądać przepisy.",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
