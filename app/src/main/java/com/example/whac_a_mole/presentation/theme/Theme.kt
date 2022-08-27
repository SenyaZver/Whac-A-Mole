package com.example.whac_a_mole.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightGreen,
    primaryVariant = Green,
    secondary = LightBrown,
    secondaryVariant = Brown,
    background = Blue,
    onBackground = LightBlack,
    surface = White

)

private val LightColorPalette = lightColors(
    primary = LightGreen,
    primaryVariant = Green,
    secondary = LightBrown,
    secondaryVariant = Brown,
    background = Blue,
    onBackground = LightBlack,
    surface = White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun WhacAMoleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}