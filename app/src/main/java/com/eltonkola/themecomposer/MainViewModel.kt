package com.eltonkola.themecomposer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eltonkola.themecomposer.data.PreferencesStore
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {

    private val preferencesStore = PreferencesStore(context)

    val darkTheme = preferencesStore.darkTheme

    fun switchTheme(darkTheme: Boolean) {
        viewModelScope.launch {
            preferencesStore.updateTheme(darkTheme)
        }
    }

}

class ViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(context) as T
    }
}
