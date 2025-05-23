package com.hfad.cocktails.data

import com.hfad.cocktails.model.Cocktail
import kotlinx.coroutines.flow.Flow

class CocktailRepository(private val dao: CocktailDao) {
    fun getAllCocktailsFlow(): Flow<List<Cocktail>> = dao.getAllCocktailsFlow()
    fun getCocktailFlowById(cocktailId: Int): Flow<Cocktail> = dao.getCocktailFlowById(cocktailId)
    suspend fun insertAll(cocktails: List<Cocktail>) = dao.insertAll(cocktails)
    suspend fun updateFavoriteStatus(cocktailId: Int, isFavorite: Boolean) {
        dao.updateFavoriteStatus(cocktailId, isFavorite)
    }
}
