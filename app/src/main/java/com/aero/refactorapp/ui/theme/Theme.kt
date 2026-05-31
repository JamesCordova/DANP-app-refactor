package com.aero.refactorapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

enum class AppThemeMode {
    BLUE,
    GREEN,
    PURPLE,
    BLACK
}

@Composable
fun RefactorAppTheme(
    themeMode: AppThemeMode,
    content: @Composable () -> Unit
) {
    val colorScheme = when(themeMode) {
        AppThemeMode.BLUE -> lightColorScheme(
            primary = BluePrimary
        )
        AppThemeMode.GREEN -> lightColorScheme(
            primary = GreenPrimary
        )
        AppThemeMode.PURPLE -> lightColorScheme(
            primary = PurplePrimary
        )
        AppThemeMode.BLACK -> lightColorScheme(
            primary = BlackPrimary
        )
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}