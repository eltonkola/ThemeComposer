package com.eltonkola.themecomposer.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.themecomposer.MainViewModel
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme
import com.eltonkola.themecomposer.data.AppThemes
import com.eltonkola.themecomposer.data.copyz
import com.eltonkola.themecomposer.data.defaultTheme
import com.eltonkola.themecomposer.data.toDarkColorRows
import com.eltonkola.themecomposer.data.toLightColorRows
import com.eltonkola.themecomposer.ui.views.ColorPicerDialog
import com.eltonkola.themecomposer.ui.views.ThemeColorColumn


@ExperimentalAnimationApi
@Composable
fun SettingsScreen(viewModel: MainViewModel) {


    Box(modifier = Modifier.fillMaxSize()){


        var openedDialog = remember { mutableStateOf(-1) }

        var appTheme : State<AppThemes> = viewModel.appTheme.collectAsState(initial = defaultTheme)

        val lightColorRows = appTheme.value.light.toLightColorRows()
        val darkColorRows = appTheme.value.dark.toDarkColorRows()

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
                    Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
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



                    Row(modifier = Modifier.fillMaxWidth()) {
                        ThemeColorColumn(
                            modifier = Modifier.weight(1f, true) ,
                            "Light colors",
                            lightColorRows){
                            openedDialog.value = it
                        }

                        Spacer(
                            modifier = Modifier
                                .width(6.dp)
                        )

                        ThemeColorColumn(
                            modifier = Modifier.weight(1f, true) ,
                            "Dark colors",
                            darkColorRows){
                            openedDialog.value = it
                        }
                    }

                }
            },

            )


        lightColorRows.forEach { kolor ->
            if (openedDialog.value == kolor.dialogId) {
                //color dialogs
                ColorPicerDialog(openedDialog.value == kolor.dialogId, kolor.color) {
                    viewModel.updateTheme(
                        appTheme.value.copyz(kolor.copy(color = it))
                    )
                    openedDialog.value = -1
                }
            }
        }

        darkColorRows.forEach { kolor ->
            if (openedDialog.value == kolor.dialogId) {
                //color dialogs
                ColorPicerDialog(openedDialog.value == kolor.dialogId, kolor.color) {
                    viewModel.updateTheme(
                        appTheme.value.copyz(kolor.copy(color = it))
                    )
                    openedDialog.value = -1
                }
            }
        }



    }


}

@ExperimentalAnimationApi
@Preview("Light Theme")
@Composable
fun SettingsScreenLightPreview() {
    val vm = MainViewModel(LocalContext.current)
    ThemeComposerTheme(viewModel = vm, darkTheme = false) {
        SettingsScreen(vm)
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme")
@Composable
fun SettingsScreenDarkPreview() {
    val vm = MainViewModel(LocalContext.current)
    ThemeComposerTheme(viewModel = vm, darkTheme = false) {
        SettingsScreen(vm)
    }
}
