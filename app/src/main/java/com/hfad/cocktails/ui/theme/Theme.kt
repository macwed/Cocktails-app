package com.hfad.cocktails.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF9C27B0),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF3E5F5),
    onPrimaryContainer = Color(0xFF3700B3),

    secondary = Color(0xFF4DB6AC),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE0F2F1),
    onSecondaryContainer = Color(0xFF00695C),

    tertiary = Color(0xFFFFCA28),
    onTertiary = Color(0xFF443300),

    background = Color(0xFFFEF6FF),
    onBackground = Color(0xFF111111),
    surface = Color.White,
    onSurface = Color(0xFF222222),
    surfaceVariant = Color(0xFFECE0F8),
    onSurfaceVariant = Color(0xFF555555),
    outline = Color(0xFF9C27B0)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFCE93D8),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF6A0080),
    onPrimaryContainer = Color.White,

    secondary = Color(0xFF80CBC4),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF263238),
    onSecondaryContainer = Color.White,

    tertiary = Color(0xFFFFE082),
    onTertiary = Color(0xFF665500),

    background = Color(0xFF18102A),
    onBackground = Color.White,
    surface = Color(0xFF24143B),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF42285E),
    onSurfaceVariant = Color(0xFFCCCCCC),
    outline = Color(0xFFCE93D8)
)

private val CocktailShapes = Shapes(
    extraSmall = RoundedCornerShape(12.dp),
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(28.dp),
    extraLarge = RoundedCornerShape(40.dp)
)

private val CocktailTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 57.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)


@Composable
fun CocktailsTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = CocktailTypography,
        shapes = CocktailShapes,
        content = content
    )
}
