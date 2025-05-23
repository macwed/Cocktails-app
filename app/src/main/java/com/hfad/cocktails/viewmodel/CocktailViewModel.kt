package com.hfad.cocktails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hfad.cocktails.data.CocktailDatabase
import com.hfad.cocktails.data.CocktailRepository
import com.hfad.cocktails.model.Cocktail
import com.hfad.cocktails.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CocktailViewModel(application: Application) : AndroidViewModel(application) {

    private val database: CocktailDatabase by lazy {
        Room.databaseBuilder(
            getApplication(),
            CocktailDatabase::class.java,
            "cocktail_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(dbSupport: SupportSQLiteDatabase) {
                    super.onCreate(dbSupport)
                    viewModelScope.launch {
                        this@CocktailViewModel.database.cocktailDao().insertAll(
                            listOf(
                                Cocktail(
                                    1, "Mojito", "Rum, limonka, mięta",
                                    "Wymieszaj składniki i podawaj na lodzie.",
                                    60, "", "Alkoholowe",
                                    R.drawable.mojito_icon, R.drawable.mojito
                                ),
                                Cocktail(
                                    2, "Martini", "Gin, wermut",
                                    "Mieszaj z lodem i przecedź do kieliszka.",
                                    90, "", "Alkoholowe",
                                    R.drawable.martini_icon, R.drawable.martini
                                ),
                                Cocktail(
                                    3, "Margarita", "TODO",
                                    "Wymieszaj składniki i podawaj na lodzie.",
                                    60, "", "Alkoholowe",
                                    R.drawable.margarita_icon, R.drawable.margarita
                                ),
                                Cocktail(
                                    4, "Cosmopolitan", "TODO",
                                    "Wymieszaj składniki w wysokiej szklance.",
                                    60, "", "Bezalkoholowe",
                                    R.drawable.cosmopolitan_icon, R.drawable.cosmopolitan
                                ),
                                Cocktail(
                                    5, "Negroni", "Gin, Campari, słodki wermut",
                                    "Wymieszaj składniki z lodem, podawaj w szklance z lodem i udekoruj skórką pomarańczową.",
                                    80, "", "Alkoholowe",
                                    R.drawable.negroni_icon, R.drawable.negroni
                                ),
                                Cocktail(
                                    6, "Daiquiri", "Rum, sok z limonki, syrop cukrowy",
                                    "Wstrząśnij składniki z lodem i przecedź do schłodzonego kieliszka.",
                                    50, "", "Alkoholowe",
                                    R.drawable.daiquiri_icon, R.drawable.daiquiri
                                ),
                                Cocktail(
                                    7, "Piña Colada", "Rum, krem kokosowy, sok ananasowy",
                                    "Zmiksuj składniki z lodem i podawaj w wysokiej szklance.",
                                    90, "", "Alkoholowe",
                                    R.drawable.pinacolada_icon, R.drawable.pinacolada
                                ),
                                Cocktail(
                                    8, "Manhattan", "TODO",
                                    "Wlej składniki do miedzianego kubka z lodem, lekko wymieszaj.",
                                    60, "", "Alkoholowe",
                                    R.drawable.manhattan_icon, R.drawable.manhattan
                                ),
                                Cocktail(
                                    9, "Aperol Spritz", "Aperol, prosecco, woda gazowana",
                                    "Wlej składniki do kieliszka z lodem i delikatnie wymieszaj.",
                                    70, "", "Alkoholowe",
                                    R.drawable.aperol_icon, R.drawable.aperol
                                ),
                            )
                        )
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }

    private val repository: CocktailRepository by lazy {
        CocktailRepository(database.cocktailDao())
    }

    val cocktails: Flow<List<Cocktail>> = repository.getAllCocktailsFlow()

    fun getCocktailFlowById(cocktailId: Int): Flow<Cocktail> =
        repository.getCocktailFlowById(cocktailId)

    fun updateFavoriteStatus(cocktailId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(cocktailId, isFavorite)
        }
    }
}
