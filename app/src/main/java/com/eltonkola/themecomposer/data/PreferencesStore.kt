package com.eltonkola.themecomposer.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class PreferencesStore(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("weather")

    private object PreferencesKeys {
        val DARK_THEME = booleanPreferencesKey("darkTheme")
    }

    val darkTheme = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.DARK_THEME] ?: true
    }

    suspend fun updateTheme(darkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_THEME] = darkTheme
        }
    }

}
