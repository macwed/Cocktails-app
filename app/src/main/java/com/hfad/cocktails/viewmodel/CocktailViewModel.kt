package com.hfad.cocktails.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hfad.cocktails.R
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
                                Cocktail(
                                    id = 1,
                                    name = "Mojito",
                                    ingredients = "Rum biały, limonka, mięta świeża, cukier trzcinowy, woda gazowana, kruszony lód",
                                    instructions = "Wygnieć miętę z cukrem i kawałkami limonki w wysokiej szklance. Dodaj rum, kruszony lód i dopełnij wodą gazowaną. Wymieszaj i udekoruj miętą.",
                                    defaultTime = 60,
                                    notes = "Orzeźwiający kubański long drink o lekko kwaskowym smaku mięty i limonki, z bąbelkami wody sodowej.",
                                    category = "Alkoholowe, Long drink",
                                    imageRes = R.drawable.mojito_icon,
                                    detailImageRes = R.drawable.mojito
                                ),
                                Cocktail(
                                    id = 2,
                                    name = "Martini",
                                    ingredients = "Gin, wytrawny wermut",
                                    instructions = "Mieszaj gin z wermutem na lodzie, przecedź do schłodzonego kieliszka koktajlowego. Udekoruj oliwką lub twistem z cytryny.",
                                    defaultTime = 90,
                                    notes = "Wytrawny short drink dla miłośników ginu i elegancji. Serwowany z oliwką.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.martini_icon,
                                    detailImageRes = R.drawable.martini
                                ),
                                Cocktail(
                                    id = 3,
                                    name = "Margarita",
                                    ingredients = "Tequila, likier triple sec, sok z limonki, sól",
                                    instructions = "Wstrząśnij tequilę, triple sec i limonkę z lodem. Brzeg kieliszka obtocz w soli, przelej koktajl do środka.",
                                    defaultTime = 60,
                                    notes = "Kwaskowa i lekko słona – ikona koktajli meksykańskich.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.margarita_icon,
                                    detailImageRes = R.drawable.margarita
                                ),
                                Cocktail(
                                    id = 4,
                                    name = "Cosmopolitan",
                                    ingredients = "Wódka, likier Cointreau, sok żurawinowy, sok z limonki",
                                    instructions = "Wstrząśnij wszystkie składniki z lodem i przecedź do kieliszka koktajlowego. Udekoruj skórką z limonki.",
                                    defaultTime = 60,
                                    notes = "Różowy short drink z nutą żurawiny i limonki. Elegancja i świeżość.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.cosmopolitan_icon,
                                    detailImageRes = R.drawable.cosmopolitan
                                ),
                                Cocktail(
                                    id = 5,
                                    name = "Negroni",
                                    ingredients = "Gin, Campari, słodki czerwony wermut",
                                    instructions = "Wymieszaj składniki z lodem, przelej do niskiej szklanki z lodem, udekoruj skórką z pomarańczy.",
                                    defaultTime = 80,
                                    notes = "Wyrazisty, gorzko-słodki włoski short drink na bazie Campari.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.negroni_icon,
                                    detailImageRes = R.drawable.negroni
                                ),
                                Cocktail(
                                    id = 6,
                                    name = "Daiquiri",
                                    ingredients = "Rum biały, sok z limonki, syrop cukrowy",
                                    instructions = "Wstrząśnij wszystkie składniki z lodem i przecedź do schłodzonego kieliszka.",
                                    defaultTime = 50,
                                    notes = "Orzeźwiający, kwaskowy short drink na bazie rumu.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.daiquiri_icon,
                                    detailImageRes = R.drawable.daiquiri
                                ),
                                Cocktail(
                                    id = 7,
                                    name = "Piña Colada",
                                    ingredients = "Rum biały, krem kokosowy, sok ananasowy",
                                    instructions = "Zmiksuj wszystkie składniki z lodem, podawaj w wysokiej szklance, udekoruj ananasem.",
                                    defaultTime = 90,
                                    notes = "Tropikalny long drink o kremowym smaku kokosa i ananasa.",
                                    category = "Alkoholowe, Long drink",
                                    imageRes = R.drawable.pinacolada_icon,
                                    detailImageRes = R.drawable.pinacolada
                                ),
                                Cocktail(
                                    id = 8,
                                    name = "Manhattan",
                                    ingredients = "Bourbon lub żytnia whiskey, słodki czerwony wermut, Angostura",
                                    instructions = "Mieszaj składniki z lodem, przecedź do kieliszka koktajlowego, udekoruj wisienką koktajlową.",
                                    defaultTime = 60,
                                    notes = "Amerykański klasyk o mocnym, lekko słodkim i ziołowym profilu smaku.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.manhattan_icon,
                                    detailImageRes = R.drawable.manhattan
                                ),
                                Cocktail(
                                    id = 9,
                                    name = "Aperol Spritz",
                                    ingredients = "Aperol, prosecco, woda gazowana, plasterek pomarańczy",
                                    instructions = "Napełnij szklankę lodem, wlej Aperol, prosecco i wodę gazowaną w proporcji 3:2:1, delikatnie wymieszaj, udekoruj pomarańczą.",
                                    defaultTime = 70,
                                    notes = "Włoski, orzeźwiający long drink – lekko gorzko-słodki i musujący.",
                                    category = "Alkoholowe, Long drink",
                                    imageRes = R.drawable.aperol_icon,
                                    detailImageRes = R.drawable.aperol
                                ),
                                Cocktail(
                                    id = 10,
                                    name = "Virgin Mojito",
                                    ingredients = "Limonka, mięta, cukier trzcinowy, woda gazowana, kruszony lód",
                                    instructions = "Wygnieć miętę i limonkę z cukrem, dodaj lód i dopełnij wodą gazowaną. Wymieszaj, udekoruj miętą.",
                                    defaultTime = 60,
                                    notes = "Orzeźwiający bezalkoholowy long drink – miętowo-limonkowy, idealny dla każdego.",
                                    category = "Bezalkoholowe, Long drink",
                                    imageRes = R.drawable.virginmojito_icon,
                                    detailImageRes = R.drawable.virginmojito
                                ),
                                Cocktail(
                                    id = 11,
                                    name = "Shirley Temple",
                                    ingredients = "Lemoniada, grenadyna, imbir piwo, wisienka koktajlowa",
                                    instructions = "Wlej grenadynę i lemoniadę do wysokiej szklanki z lodem, dopełnij imbirowym piwem, udekoruj wisienką.",
                                    defaultTime = 60,
                                    notes = "Słodko-owocowy, wiśniowy klasyk dla dzieci i dorosłych.",
                                    category = "Bezalkoholowe, Long drink",
                                    imageRes = R.drawable.shirleytemple_icon,
                                    detailImageRes = R.drawable.shirleytemple
                                ),
                                Cocktail(
                                    id = 12,
                                    name = "Cucumber Cooler",
                                    ingredients = "Ogórek świeży, limonka, syrop cukrowy, mięta, woda gazowana",
                                    instructions = "Wygnieć ogórek z limonką i syropem, dodaj miętę, wstrząśnij z lodem, przecedź do szklanki, dopełnij wodą gazowaną.",
                                    defaultTime = 60,
                                    notes = "Orzeźwiający i lekko wytrawny bezalkoholowy short drink na bazie ogórka, limonki i mięty.",
                                    category = "Bezalkoholowe, Short drink",
                                    imageRes = R.drawable.cucumbercooler_icon,
                                    detailImageRes = R.drawable.cucumbercooler
                                ),
                                Cocktail(
                                    id = 13,
                                    name = "Espresso Martini",
                                    ingredients = "Wódka, likier kawowy, świeże espresso",
                                    instructions = "Wstrząśnij wszystkie składniki z lodem, przecedź do kieliszka koktajlowego, udekoruj ziarnami kawy.",
                                    defaultTime = 60,
                                    notes = "Wytrawny, intensywny short drink o smaku kawy i wódki.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.espressomartini_icon,
                                    detailImageRes = R.drawable.espressomartini
                                ),
                                Cocktail(
                                    id = 14,
                                    name = "Whiskey Sour",
                                    ingredients = "Bourbon, sok z cytryny, syrop cukrowy, białko jajka, Angostura",
                                    instructions = "Wstrząśnij wszystko bez lodu (dry shake), potem z lodem, przecedź do niskiej szklanki, udekoruj plasterkiem cytryny.",
                                    defaultTime = 60,
                                    notes = "Kwaśny, lekko słodki short drink z bourbonem i pianką z białka.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.whiskeysour_icon,
                                    detailImageRes = R.drawable.whiskeysour
                                ),
                                Cocktail(
                                    id = 15,
                                    name = "White Lady",
                                    ingredients = "Gin, likier triple sec, sok z cytryny",
                                    instructions = "Wstrząśnij składniki z lodem, przecedź do schłodzonego kieliszka koktajlowego.",
                                    defaultTime = 60,
                                    notes = "Lekko wytrawny, cytrusowy short drink na bazie ginu i triple sec.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.whitelady_icon,
                                    detailImageRes = R.drawable.whitelady
                                ),
                                Cocktail(
                                    id = 16,
                                    name = "Kamikaze",
                                    ingredients = "Wódka, likier triple sec, sok z limonki",
                                    instructions = "Wstrząśnij wszystkie składniki z lodem, przecedź do kieliszka.",
                                    defaultTime = 60,
                                    notes = "Orzeźwiający, mocny i cytrusowy short drink.",
                                    category = "Alkoholowe, Short drink",
                                    imageRes = R.drawable.kamikaze_icon,
                                    detailImageRes = R.drawable.kamikaze
                                ),
                                Cocktail(
                                    id = 17,
                                    name = "Cuba Libre",
                                    ingredients = "Rum biały, cola, limonka",
                                    instructions = "Napełnij szklankę lodem, dodaj rum i colę, wyciśnij limonkę, zamieszaj i udekoruj limonką.",
                                    defaultTime = 60,
                                    notes = "Karaibski long drink o wyraźnym smaku rumu i coli.",
                                    category = "Alkoholowe, Long drink",
                                    imageRes = R.drawable.cubalibre_icon,
                                    detailImageRes = R.drawable.cubalibre
                                ),
                                Cocktail(
                                    id = 18,
                                    name = "Tequila Sunrise",
                                    ingredients = "Tequila, sok pomarańczowy, grenadyna",
                                    instructions = "Napełnij szklankę lodem, wlej tequilę i sok pomarańczowy, delikatnie dolej grenadynę, nie mieszaj – powstanie efekt wschodu słońca.",
                                    defaultTime = 60,
                                    notes = "Zachwycający long drink o kolorze wschodzącego słońca.",
                                    category = "Alkoholowe, Long drink",
                                    imageRes = R.drawable.tequilasunrise_icon,
                                    detailImageRes = R.drawable.tequilasunrise
                                )
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
