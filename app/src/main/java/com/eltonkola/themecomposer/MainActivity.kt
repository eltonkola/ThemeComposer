package com.eltonkola.themecomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.eltonkola.themecomposer.ui.MainAppScreen
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.LocalSystemUiController
import com.google.accompanist.systemuicontroller.rememberAndroidSystemUiController

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[MainViewModel::class.java]

        setContent {
            val darkTheme = viewModel.darkTheme.collectAsState(initial = isSystemInDarkTheme())
            ThemeComposerTheme(viewModel = viewModel, darkTheme = darkTheme.value) {
                ProvideWindowInsets(){
                    Surface(color = MaterialTheme.colors.background) {
                        val controller = rememberAndroidSystemUiController()
                            CompositionLocalProvider(LocalSystemUiController provides controller) {

                                val systemUiController = LocalSystemUiController.current
                                val useDarkIcons = MaterialTheme.colors.isLight
                                val secondary = MaterialTheme.colors.secondary
                                val onSecondary = MaterialTheme.colors.onSecondary

                                SideEffect {
                                    // Update all of the system bar colors to be transparent, and use
                                    // dark icons if we're in light theme
                                    systemUiController.setSystemBarsColor(
                                        color = secondary,
                                        darkIcons = useDarkIcons,
                                    )
//                                    systemUiController.setStatusBarColor(onSecondary)
//                                    systemUiController.setNavigationBarColor(onSecondary, useDarkIcons)

                                }


                                MainAppScreen(viewModel)
                            }
                        }
                    }
                }
            }
    }
}
