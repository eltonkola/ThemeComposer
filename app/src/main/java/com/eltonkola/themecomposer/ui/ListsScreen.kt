package com.eltonkola.themecomposer.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme

@Composable
fun ListsScreen() {
    Text("ListsScreen")
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun ListsScreenLightPreview() {
    ThemeComposerTheme(darkTheme = false) {
        ListsScreen()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ListsScreenDarkPreview() {
    ThemeComposerTheme(darkTheme = true) {
        ListsScreen()
    }
}
