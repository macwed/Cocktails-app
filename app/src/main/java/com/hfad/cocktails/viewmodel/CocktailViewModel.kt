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
                                Cocktail(1, "Mojito", "Rum, limonka, mięta", "Wymieszaj składniki i podawaj na lodzie.", 60),
                                Cocktail(2, "Martini", "Gin, wermut", "Mieszaj z lodem i przecedź do kieliszka.", 90),
                                Cocktail(3, "Margarita", "Tequila, limonka, triple sec", "Wstrząśnij z lodem i podawaj w solonym kieliszku.", 120),
                                Cocktail(4, "Cosmopolitan", "Wódka, triple sec, sok żurawinowy, świeża limonka", "Wstrząśnij składniki z lodem i przecedź do schłodzonego kieliszka.", 70),
                                Cocktail(5, "Negroni", "Gin, Campari, słodki wermut", "Wymieszaj składniki z lodem, podawaj w szklance z lodem i udekoruj skórką pomarańczową.", 80),
                                Cocktail(6, "Daiquiri", "Rum, sok z limonki, syrop cukrowy", "Wstrząśnij składniki z lodem i przecedź do schłodzonego kieliszka.", 50),
                                Cocktail(7, "Moscow Mule", "Wódka, piwo imbirowe, sok z limonki", "Wlej składniki do miedzianego kubka z lodem, lekko wymieszaj.", 60),
                                Cocktail(8, "Piña Colada", "Rum, krem kokosowy, sok ananasowy", "Zmiksuj składniki z lodem i podawaj w wysokiej szklance.", 90),
                                Cocktail(9, "Sidecar", "Konjak, triple sec, sok z cytryny", "Wstrząśnij składniki z lodem i przelej do kieliszka koktajlowego.", 75),
                                Cocktail(10, "Mint Julep", "Bourbon, świeża mięta, cukier, woda", "Ugnieć mięte z cukrem, dodaj bourbon i lód, delikatnie mieszaj.", 85),
                                Cocktail(11, "Sazerac", "Rye whiskey, absynt, cukier, bitter", "Wymieszaj składniki, schłodź kieliszek i podawaj.", 95),
                                Cocktail(12, "Tom Collins", "Gin, sok z cytryny, cukier, soda", "Wymieszaj składniki z lodem i dopełnij sodą.", 65),
                                Cocktail(13, "Aperol Spritz", "Aperol, prosecco, woda gazowana", "Wlej składniki do kieliszka z lodem i delikatnie wymieszaj.", 70)
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

    suspend fun getCocktailById(cocktailId: Int): Cocktail? = repository.getCocktailById(cocktailId)
}
