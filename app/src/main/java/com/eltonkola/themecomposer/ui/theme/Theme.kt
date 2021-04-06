package com.eltonkola.themecomposer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.eltonkola.themecomposer.MainViewModel
import com.eltonkola.themecomposer.data.AppThemes
import com.eltonkola.themecomposer.data.defaultTheme

@Composable
fun ThemeComposerTheme(
    viewModel: MainViewModel,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {

    var appTheme : State<AppThemes> = viewModel.appTheme.collectAsState(initial = defaultTheme)


    val darkColorPalette = darkColors(
        primary = appTheme.value.dark.primary,
        primaryVariant = appTheme.value.dark.primaryVariant,
        secondary = appTheme.value.dark.secondary,
        background = appTheme.value.dark.background,
        surface = appTheme.value.dark.surface,
        onPrimary = appTheme.value.dark.onPrimary,
        onSecondary = appTheme.value.dark.onSecondary,
        onBackground = appTheme.value.dark.onBackground,
        onSurface = appTheme.value.dark.onSurface,
    )

    val lightColorPalette = lightColors(
        primary = appTheme.value.light.primary,
        primaryVariant = appTheme.value.light.primaryVariant,
        secondary = appTheme.value.light.secondary,
        background = appTheme.value.light.background,
        surface = appTheme.value.light.surface,
        onPrimary = appTheme.value.light.onPrimary,
        onSecondary = appTheme.value.light.onSecondary,
        onBackground = appTheme.value.light.onBackground,
        onSurface = appTheme.value.light.onSurface,
    )



    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}