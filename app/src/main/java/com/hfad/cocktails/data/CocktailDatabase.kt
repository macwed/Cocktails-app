package com.hfad.cocktails.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hfad.cocktails.model.Cocktail

@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}
