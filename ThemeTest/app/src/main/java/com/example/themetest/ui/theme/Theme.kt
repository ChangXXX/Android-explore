package com.example.themetest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = Color.Magenta,
    onPrimary = Color.Cyan,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = Color.Black,
    onSurface = Color.White,

    /* Other default colors to override
    background = Color.White,
    onBackground = Color.Black,
    */
)

@Composable
fun ThemeTestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
