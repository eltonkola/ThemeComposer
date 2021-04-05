package com.eltonkola.themecomposer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.themecomposer.MainViewModel
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme

@Composable
fun SettingsScreen(viewModel: MainViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        },
        content = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ),
            ) {

                // change theme
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Use the dark theme",
                        modifier = Modifier.weight(1f)
                    )
                    val darkTheme = viewModel.darkTheme.collectAsState(initial = isSystemInDarkTheme())

                    Switch(
                        checked = darkTheme.value,
                        onCheckedChange = {
                            viewModel.switchTheme(!darkTheme.value)
                        },
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(MaterialTheme.colors.secondary)
                        .alpha(0.5f)
                )




            }
        },

    )
}

@Preview("Light Theme")
@Composable
fun SettingsScreenLightPreview() {
    ThemeComposerTheme(darkTheme = false) {
        SettingsScreen(MainViewModel(LocalContext.current))
    }
}

@Preview("Dark Theme")
@Composable
fun SettingsScreenDarkPreview() {
    ThemeComposerTheme(darkTheme = true) {
        SettingsScreen(MainViewModel(LocalContext.current))
    }
}
