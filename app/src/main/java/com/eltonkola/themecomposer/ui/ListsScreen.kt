package com.eltonkola.themecomposer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage

@Composable
fun ListsScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lists and cards") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        },
        content = {
            LazyColumn() {

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        elevation = 8.dp,
                    ){
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "This is a Card"
                        )
                    }
                }

                items(10){
                    RowCard()
                }


            }
        }
    )
}


@Composable
fun RowCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = 8.dp,
    ) {
        Column() {
            CoilImage(
                data = "https://picsum.photos/300/300",
                contentDescription = "My content description",
                loading = {
                    Box(Modifier.matchParentSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
//              error = {
//                  Image(asset = imageResource(R.drawable.ic_error))
//              }
            )
        }

        Text(
            modifier = Modifier.padding(8.dp),
            text = "This is a Card"
        )
    }


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
