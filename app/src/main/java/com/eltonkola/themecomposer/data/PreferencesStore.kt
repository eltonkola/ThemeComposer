package com.eltonkola.themecomposer.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


data class AppThemeColors(
        val primary: Color,
        val primaryVariant: Color,
        val secondary: Color,
        val background: Color,
        val surface: Color,
        val onPrimary: Color,
        val onSecondary: Color,
        val onBackground: Color,
        val onSurface: Color
    )

data class AppThemes(
    val light : AppThemeColors,
    val dark : AppThemeColors
)

fun AppThemes.copyz(row: ColorRow) : AppThemes {
    return this.copy(
        light = this.light.copy(
            primary = if(row.dialogId == 0) row.color else this.light.primary,
            primaryVariant = if(row.dialogId == 1) row.color else this.light.primaryVariant,
            secondary = if(row.dialogId == 2) row.color else this.light.secondary,
            background = if(row.dialogId == 3) row.color else this.light.background,
            surface = if(row.dialogId == 4) row.color else this.light.surface,
            onPrimary = if(row.dialogId == 5) row.color else this.light.onPrimary,
            onSecondary = if(row.dialogId == 6) row.color else this.light.onSecondary,
            onBackground = if(row.dialogId == 7) row.color else this.light.onBackground,
            onSurface = if(row.dialogId == 8) row.color else this.light.onSurface,
        ),
        dark = this.dark.copy(
            primary = if(row.dialogId == 9) row.color else this.dark.primary,
            primaryVariant = if(row.dialogId == 10) row.color else this.dark.primaryVariant,
            secondary = if(row.dialogId == 11) row.color else this.dark.secondary,
            background = if(row.dialogId == 12) row.color else this.dark.background,
            surface = if(row.dialogId == 13) row.color else this.dark.surface,
            onPrimary = if(row.dialogId == 14) row.color else this.dark.onPrimary,
            onSecondary = if(row.dialogId == 15) row.color else this.dark.onSecondary,
            onBackground = if(row.dialogId == 16) row.color else this.dark.onBackground,
            onSurface = if(row.dialogId == 17) row.color else this.dark.onSurface,
        )
    )
}

data class ColorRow(val name: String, val dialogId: Int, val color: Color)

fun AppThemeColors.toLightColorRows() : List<ColorRow>{
    return listOf(
        ColorRow("Primary", 0, this.primary),
        ColorRow("Primary Variant", 1, this.primaryVariant),
        ColorRow("Secondary", 2, this.secondary),
        ColorRow("Background", 3, this.background),
        ColorRow("Surface", 4, this.surface),
        ColorRow("OnPrimary", 5, this.onPrimary),
        ColorRow("OnSecondary", 6, this.onSecondary),
        ColorRow("OnBackground", 7, this.onBackground),
        ColorRow("OnSurface", 8, this.onSurface),
    )
}

fun AppThemeColors.toDarkColorRows() : List<ColorRow>{
    return listOf(
        ColorRow("Primary", 9, this.primary),
        ColorRow("Primary Variant", 10, this.primaryVariant),
        ColorRow("Secondary", 11, this.secondary),
        ColorRow("Background", 12, this.background),
        ColorRow("Surface", 13, this.surface),
        ColorRow("OnPrimary", 14, this.onPrimary),
        ColorRow("OnSecondary", 15, this.onSecondary),
        ColorRow("OnBackground", 16, this.onBackground),
        ColorRow("OnSurface", 17, this.onSurface),
    )
}

val defaultTheme = AppThemes(
    light = AppThemeColors(
        primary = Color(0xFF00897B),
        primaryVariant = Color(0xFFD81B60),
        secondary = Color(0xFF4EBAAA),
        background = Color(0xFFEFEEF0),
        surface = Color(0xFFF5F2FA),
        onPrimary = Color(0xFF0D021A),
        onSecondary = Color(0xFF1B003C),
        onBackground = Color(0xFF0F021F),
        onSurface = Color(0xFF24103D),
    ),
    dark = AppThemeColors(
        primary = Color(0xFF00897B),
        primaryVariant = Color(0xFFD81B60),
        secondary = Color(0xFF005B4F),
        background = Color(0xFF001C2B),
        surface = Color(0xFF05010A),
        onPrimary = Color(0xFFBCB5C4),
        onSecondary = Color(0xFF7E7C81),
        onBackground = Color(0xFFFFFFFF),
        onSurface = Color(0xFFE3D7F3),
    )
)




@ExperimentalUnsignedTypes
class PreferencesStore(private val context: Context) {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val Context.dataStore by preferencesDataStore("theme_composer")
    private object ThemeKeys {
        val DARK_THEME = booleanPreferencesKey("darkTheme")
        val THEME = stringPreferencesKey("theme")
    }

    val darkTheme = context.dataStore.data.map { preferences ->
        preferences[ThemeKeys.DARK_THEME] ?: true
    }

    suspend fun updateTheme(darkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ThemeKeys.DARK_THEME] = darkTheme
        }
    }


    val appTheme : Flow<AppThemes> = context.dataStore.data.map { preferences ->
        val theme = preferences[ThemeKeys.THEME]
        if(theme !=null){
            gson.fromJson(theme, AppThemes::class.java)  as AppThemes
        }else{
            defaultTheme
        }
    }

    suspend fun updateTheme(appTheme: AppThemes) {
        context.dataStore.edit { preferences ->
            preferences[ThemeKeys.THEME] = gson.toJson(appTheme)
        }
    }

}
