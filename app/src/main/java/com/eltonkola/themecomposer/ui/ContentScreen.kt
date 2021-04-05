package com.eltonkola.themecomposer.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme

@Composable
fun ContentScreen() {
    Text("Content")
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun ContentScreenLightPreview() {
    ThemeComposerTheme(darkTheme = false) {
        ContentScreen()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ContentScreenDarkPreview() {
    ThemeComposerTheme(darkTheme = true) {
        ContentScreen()
    }
}
