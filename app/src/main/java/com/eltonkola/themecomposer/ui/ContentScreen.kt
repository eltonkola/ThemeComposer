package com.eltonkola.themecomposer.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ContentScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Text") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        },
        content = {

            LazyColumn(
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

                item {
                    Text("Just Text")
                }
                item {
                    Text(
                        "Text with cursive font",
                        style = TextStyle(fontFamily = FontFamily.Cursive)
                    )
                }
                item {
                    Text(
                        text = "Text with LineThrough",
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                }
                item {
                    Text(
                        text = "Text with underline",
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                }
                item {
                    Text(
                        text = "Text with underline, linethrough and bold",
                        style = TextStyle(
                            textDecoration = TextDecoration.combine(
                                listOf(
                                    TextDecoration.Underline,
                                    TextDecoration.LineThrough
                                )
                            ), fontWeight = FontWeight.Bold
                        )
                    )
                }
                item {


                    Text("H1", style = MaterialTheme.typography.h1)
                }
                item {
                    Text("Lorem H2", style = MaterialTheme.typography.h2)
                }
                item {
                    Text("Lorem Ipsum H3", style = MaterialTheme.typography.h3)
                }
                item {
                    Text("Lorem Ipsum H4", style = MaterialTheme.typography.h4)
                }
                item {
                    Text("Lorem Ipsum H5", style = MaterialTheme.typography.h5)
                }
                item {
                    Text("Lorem Ipsum H6", style = MaterialTheme.typography.h6)
                }
                item {
                    Text(
                        "Body 1 Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
                        style = MaterialTheme.typography.body1
                    )
                }
                item {
                    Text(
                        "Body 2 Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
                        style = MaterialTheme.typography.body2
                    )
                }
                item {
                    Text("Lorem Ipsum Subtitle 1", style = MaterialTheme.typography.subtitle1)
                }
                item {
                    Text("Lorem Ipsum Subtitle 2", style = MaterialTheme.typography.subtitle2)
                }
                item {
                    Text("Lorem Ipsum overline", style = MaterialTheme.typography.overline)
                }
                item {
                    Text("Lorem Ipsum caption", style = MaterialTheme.typography.caption)
                }
                item {
                    Text("Lorem Ipsum button", style = MaterialTheme.typography.button)
                }


            }
        })

}

//@Preview("Light Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun ContentScreenLightPreview() {
//    ThemeComposerTheme(darkTheme = false) {
//        ContentScreen()
//    }
//}
//
//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun ContentScreenDarkPreview() {
//    ThemeComposerTheme(darkTheme = true) {
//        ContentScreen()
//    }
//}
