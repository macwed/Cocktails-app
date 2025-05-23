package com.hfad.cocktails.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hfad.cocktails.model.Cocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
    @Query("SELECT * FROM cocktails")
    fun getAllCocktails(): Flow<List<Cocktail>>

    @Query("SELECT * FROM cocktails WHERE id = :cocktailId LIMIT 1")
    suspend fun getCocktailById(cocktailId: Int): Cocktail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(cocktail: Cocktail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cocktails: List<Cocktail>)

    @Query("UPDATE cocktails SET isFavorite = :isFavorite WHERE id = :cocktailId")
    suspend fun updateFavoriteStatus(cocktailId: Int, isFavorite: Boolean)
}
