package com.eltonkola.themecomposer.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.eltonkola.themecomposer.MainViewModel
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme


sealed class AppTabs(val icon: ImageVector, val title: String, val route: String) {
    object Content : AppTabs(Icons.Default.Home, "Home", "home")
    object Lists : AppTabs(Icons.Default.List, "Lists", "lists")
    object Settings : AppTabs(Icons.Default.Settings, "Settings", "settings")
}

@Composable
fun MainAppScreen(viewModel: MainViewModel) {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    val tabs = listOf(AppTabs.Content, AppTabs.Lists, AppTabs.Settings)

    Scaffold(
        bottomBar = {
            if (currentRoute in tabs.map { it.route }) {

                BottomNavigation {
                    tabs.forEach { screen ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = screen.title
                                )
                            },
                            label = { Text(screen.title) },
                            selected = currentRoute == screen.route,
                            alwaysShowLabel = true,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route)
                                }
                            }
                        )
                    }
                }
            }
        },
    ) {
        NavHost(navController, startDestination = AppTabs.Content.route) {
            composable(AppTabs.Content.route) {
                ContentScreen()
            }
            composable(AppTabs.Lists.route) {
                ListsScreen()
            }
            composable(AppTabs.Settings.route) {
                SettingsScreen(viewModel)
            }
        }
    }

}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainAppScreenLightPreview() {
    ThemeComposerTheme(darkTheme = false) {
        MainAppScreen(MainViewModel(LocalContext.current))
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainAppScreenDarkPreview() {
    ThemeComposerTheme(darkTheme = true) {
        MainAppScreen(MainViewModel(LocalContext.current))
    }
}
