package com.eltonkola.themecomposer.ui.views

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.demos.ColorPicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun ColorPicerDialog(
    showDialog : Boolean = false,
    color: Color,
    onPick:(Color) -> Unit
) {


    val dialogBackgroundColor: Color by animateColorAsState(
        targetValue = if (showDialog) MaterialTheme.colors.primaryVariant.copy(alpha = 0.5f) else Color.Transparent,
        animationSpec = tween(3000, easing = LinearEasing)
    )

    val dialogScale: Float by animateFloatAsState(
        if (showDialog) 1f else 0f,
        // tween(300, easing = LinearEasing)
        spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dialogBackgroundColor)
            .padding(52.dp),
        contentAlignment = Alignment.Center,
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    scaleX = dialogScale
                    scaleY = dialogScale
                    alpha = 0.9f
                },
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            //  contentColor = Color.White
        ) {

            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Game won",
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }

                var primary by remember { mutableStateOf(color) }
                TopAppBar(title = { Text("Color Picker") }, backgroundColor = primary)
                ColorPicker(onColorChange = { primary = it })

                Slider(
                    value = primary.red,
                    onValueChange = { red ->
                        primary = primary.copy(red = red)
                    },
                )

                Slider(
                    value = primary.green,
                    onValueChange = { green ->
                        primary = primary.copy(green = green)
                    },
                )

                Slider(
                    value = primary.blue,
                    onValueChange = { blue ->
                        primary = primary.copy(blue = blue)
                    },
                )

                Slider(
                    value = primary.alpha,
                    onValueChange = { alpha ->
                        primary = primary.copy(alpha = alpha)
                    },
                )



                        Button(
                            modifier = Modifier.padding(8.dp)
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            shape = RoundedCornerShape(50),
                            onClick = {
//                                navController.popBackStack()
//                                gameWon.value = false
                                onPick(primary)
                            }
                        ) {
                            Text(text = "Pick Color")
                        }

                }


        }
    }
}

