package com.hfad.cocktails.data

import com.hfad.cocktails.model.Cocktail
import kotlinx.coroutines.flow.Flow

class CocktailRepository(private val dao: CocktailDao) {
    fun getAllCocktailsFlow(): Flow<List<Cocktail>> = dao.getAllCocktails()
    suspend fun getCocktailById(cocktailId: Int): Cocktail? = dao.getCocktailById(cocktailId)
    suspend fun insertAll(cocktails: List<Cocktail>) = dao.insertAll(cocktails)
}
