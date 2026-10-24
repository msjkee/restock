package com.griffith.restock.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Forest,
    onPrimary = Color.White,
    primaryContainer = Sage,
    onPrimaryContainer = Forest,
    secondary = MutedGreen,
    secondaryContainer = Sage,
    onSecondaryContainer = Forest,
    tertiary = Amber,
    tertiaryContainer = SoftAmber,
    onTertiaryContainer = Amber,
    error = Clay,
    errorContainer = SoftClay,
    onErrorContainer = Clay,
    background = Cream,
    onBackground = Ink,
    surface = Cream,
    onSurface = Ink,
    surfaceVariant = Color(0xFFEAEDE5),
    onSurfaceVariant = MutedGreen,
    surfaceContainer = Color(0xFFF0F1E9),
    surfaceContainerLow = Color(0xFFFEFDF9),
    outline = Color(0xFF78877B),
    outlineVariant = Color(0xFFDEE3D9)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFA7D2B6),
    onPrimary = Color(0xFF103A28),
    primaryContainer = Color(0xFF294E3B),
    onPrimaryContainer = Color(0xFFC5EBD1),
    secondary = Color(0xFFB6CDBD),
    secondaryContainer = Color(0xFF334C3D),
    onSecondaryContainer = Color(0xFFD4E8D9),
    tertiary = Color(0xFFF0C36C),
    tertiaryContainer = Color(0xFF584116),
    onTertiaryContainer = Color(0xFFFFDDA0),
    error = Color(0xFFFFB4A6),
    errorContainer = Color(0xFF71372D),
    onErrorContainer = Color(0xFFFFDAD2),
    background = Color(0xFF141C17),
    onBackground = Color(0xFFE1E8DF),
    surface = Color(0xFF141C17),
    onSurface = Color(0xFFE1E8DF),
    surfaceVariant = Color(0xFF354239),
    onSurfaceVariant = Color(0xFFBAC8BD),
    surfaceContainer = Color(0xFF202B23),
    surfaceContainerLow = Color(0xFF1B251E),
    outline = Color(0xFF8D9B90),
    outlineVariant = Color(0xFF354239)
)

@Composable
fun RestockTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}
