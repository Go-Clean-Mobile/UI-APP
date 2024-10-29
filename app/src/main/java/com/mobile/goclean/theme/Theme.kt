package com.mobile.goclean.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.mobile.goclean.R

val productsans = FontFamily(
    Font(R.font.productsans, FontWeight.Normal),
)

val ooredo = FontFamily(
    Font(R.font.ooredo, FontWeight.Normal),
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue600,
    secondary = Slate400,
    tertiary = Red400,
    background = Gray800,
    surface = Color.Black,
    onPrimary = Color.White,
    onSecondary = Gray50,
    onTertiary = Color.White,
    onBackground = Gray50,
    onSurface = Gray50
)

private val LightColorScheme = lightColorScheme(
    primary = Blue600,
    secondary = Slate400,
    tertiary = Red400,
    background = Gray50,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Gray800,
    onTertiary = Color.White,
    onBackground = Gray800,
    onSurface = Gray800
)

@Composable
fun GoCleanTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}