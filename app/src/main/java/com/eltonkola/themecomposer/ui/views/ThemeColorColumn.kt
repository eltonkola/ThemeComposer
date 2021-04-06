package com.eltonkola.themecomposer.ui.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.eltonkola.themecomposer.data.ColorRow


@ExperimentalAnimationApi
@Composable
fun ThemeColorColumn(modifier : Modifier,
                     title: String,
                     colors: List<ColorRow>,
                     onSelect: (Int) -> Unit) {

    Column(
        modifier = modifier
    ) {


        Text(
            modifier = Modifier.padding(top = 6.dp, bottom = 4.dp),
            text = title,
            style  = MaterialTheme.typography.h5,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(MaterialTheme.colors.secondary)
                .alpha(0.5f))

        colors.forEach { kolor ->

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f),
                    text = kolor.name
                )
                Button(
                    modifier = Modifier.size(28.dp),
                    onClick = {
                        onSelect(kolor.dialogId)
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = kolor.color)
                ){

                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(MaterialTheme.colors.secondary)
                    .alpha(0.5f)
            )

        }
    }
}
