package com.eltonkola.themecomposer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DrawerValue
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Snackbar
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.themecomposer.ui.theme.ThemeComposerTheme
import kotlinx.coroutines.launch



@Composable
fun EmptySpace() {
    Box(modifier = Modifier.size(16.dp))
}

@Composable
fun FormsScreen() {

    var progress = remember { mutableStateOf(0.5f) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forms") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            Column {
                Text("Text in Drawer")
                Button(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }) {
                    Text("Close Drawer")
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(bottom = 50.dp),
                icon = { Icon(Icons.Filled.Favorite,"") },
                text = { Text("Floating Action Button") },
                onClick = {
                            progress.value = progress.value + 0.1F
                            if(progress.value >= 1f){
                                progress.value = 0f
                            }
                          },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {

                var openedDialog = remember { mutableStateOf(false) }

                //butotn
                Button(onClick = {
                    openedDialog.value = true
                }) {
                    Text("Open dialog")
                }

                if (openedDialog.value) {

                    AlertDialog(
                        onDismissRequest = {
                            // Dismiss the dialog when the user clicks outside the dialog or on the back
                            // button. If you want to disable that functionality, simply use an empty
                            // onCloseRequest.
                            openedDialog.value = false
                        },
                        title = {
                            Text(text = "Dialog Title")
                        },
                        text = {
                            Text("Here is a text ")
                        },
                        confirmButton = {
                            Button(

                                onClick = {
                                    openedDialog.value = false
                                }) {
                                Text("This is the Confirm Button")
                            }
                        },
                        dismissButton = {
                            Button(

                                onClick = {
                                    openedDialog.value = false
                                }) {
                                Text("This is the dismiss Button")
                            }
                        }
                    )
                }


                EmptySpace()

                val snackbarVisibleState = remember { mutableStateOf(false) }

                Button(
                    onClick = { snackbarVisibleState.value = !snackbarVisibleState.value }) {
                    if (snackbarVisibleState.value) {
                        Text("Hide Snackbar")
                    } else {
                        Text("Show Snackbar")
                    }
                }
                if (snackbarVisibleState.value) {
                    Snackbar(

                        action = {
                            Button(onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                                Text("Open side bar")
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) { Text(text = "This is a snackbar!") }
                }

                EmptySpace()


                //progress
                CircularProgressIndicator(progress = progress.value)

                EmptySpace()

                //drop down menu
                var expanded =  remember { mutableStateOf(false) }
                val items = listOf("Drop down value", "B", "C", "D", "E", "F")
                val disabledValue = "B"
                var selectedIndex = remember { mutableStateOf(0) }

                    Text(items[selectedIndex.value],modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded.value = true
                        })
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            items.forEachIndexed { index, s ->
                                DropdownMenuItem(onClick = {
                                    selectedIndex.value = index
                                    expanded.value = false
                                }) {
                                    val disabledText = if (s == disabledValue) {
                                        " (Disabled)"
                                    } else {
                                        ""
                                    }
                                    Text(text = s + disabledText)
                                }
                            }
                        }
                EmptySpace()

                //check box
                val checkedState = remember { mutableStateOf(true) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it }
                )
                EmptySpace()

                //lienar progress
                LinearProgressIndicator(progress = progress.value)
                EmptySpace()

                //radio button

                val radioOptions = listOf("A", "B", "C")
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
                Column {
                    radioOptions.forEach { text ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected
                                        (text)
                                    }
                                )
                                .padding(horizontal = 16.dp)
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
                EmptySpace()

                //slider

                var sliderPosition =  remember { mutableStateOf(0f) }
                Text(text = sliderPosition.toString())
                Slider(value = sliderPosition.value, onValueChange = { sliderPosition.value = it })
                EmptySpace()

                //switch
                val checkedStateZ = remember { mutableStateOf(true) }
                Switch(
                    checked = checkedStateZ.value,
                    onCheckedChange = { checkedStateZ.value = it }
                )
                EmptySpace()

                //input

                val textState = remember { mutableStateOf(TextFieldValue()) }
                TextField(
                    value = textState.value,
                    onValueChange = { textState.value = it }
                )
                Text("The textfield has this text: " + textState.value.text)
                EmptySpace()


            }
        }
    )
}

//@Preview("Light Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun ListsScreenLightPreview() {
//    ThemeComposerTheme(darkTheme = false) {
//        ListsScreen()
//    }
//}
//
//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun ListsScreenDarkPreview() {
//    ThemeComposerTheme(darkTheme = true) {
//        ListsScreen()
//    }
//}
