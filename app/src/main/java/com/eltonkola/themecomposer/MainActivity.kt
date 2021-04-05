package com.eltonkola.themecomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.eltonkola.themecomposer.ui.MainAppScreen
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[MainViewModel::class.java]

        setContent {
            val darkTheme = viewModel.darkTheme.collectAsState(initial = isSystemInDarkTheme())
            ThemeComposerTheme(darkTheme = darkTheme.value) {
                Surface(color = MaterialTheme.colors.background) {
                    MainAppScreen(viewModel)
                }
            }
        }
    }
}
