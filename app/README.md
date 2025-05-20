# Cocktails-app

A simple bartender’s cocktail recipe app built with Kotlin, Jetpack Compose, and Room.

---

## Features

* **Pre-populated recipes**: 13 classic cocktails available on first launch.
* **Master–Detail Layout**:

    * **Phone (portrait)**: Navigate from list to detail screen.
    * **Tablet / Landscape**: Split view with list on the left and details on the right.
* **Detail Screen**: Shows ingredients, instructions, optional notes, and a countdown timer.
* **Timer**: Start, pause, reset, and set custom time. State survives configuration changes.
* **Local Storage**: Data stored in a Room database (no network required).

---

## Project Structure

```
com.hfad.cocktails
├── data
│   ├── CocktailDao.kt
│   ├── CocktailDatabase.kt
│   └── CocktailRepository.kt
├── model
│   └── Cocktail.kt
├── ui
│   ├── CocktailListScreen.kt
│   ├── CocktailDetailScreen.kt
│   ├── MasterDetailScreen.kt
│   └── TimerScreen.kt
├── viewmodel
│   ├── CocktailViewModel.kt
│   └── TimerViewModel.kt
├── MainActivity.kt
└── DetailActivity.kt
```

---

## Getting Started

1. **Clone** the repository:

   ```bash
   git clone https://github.com/<your-username>/Cocktails-app.git
   ```
2. **Open** the project in Android Studio (Arctic Fox or newer).
3. **Run** on an emulator or physical device.

---

## Notes

* Recipes are pre-loaded via Room’s onCreate callback—no UI for adding recipes.
* Feel free to fork for experimentation.

---

## License

Released under the [MIT License](LICENSE).