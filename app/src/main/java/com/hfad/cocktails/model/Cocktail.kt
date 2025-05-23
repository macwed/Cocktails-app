package com.hfad.cocktails.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class Cocktail(
    @PrimaryKey val id: Int,
    val name: String,
    val ingredients: String,
    val instructions: String,
    val defaultTime: Int = 60,          // czas domyślny
    val notes: String = "",
    val category: String = "Alkoholowe",
    val imageRes: Int = 0,
    val detailImageRes: Int = 0,
    val isFavorite: Boolean = false
)
