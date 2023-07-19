package com.example.composecolorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min

@Composable
fun ColorPicker(rgb: Color, onColorChanged: (Color) -> Unit) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        val width by remember { mutableStateOf(min(200.dp, maxWidth)) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = rgb.complementary(),
                    ),
                text = "현재 텍스트 색상입니다.",
                style = TextStyle(
                    color = rgb,
                ),
                textAlign = TextAlign.Center,
            )
            Slider(
                modifier = Modifier.width(width),
                value = rgb.red,
                onValueChange = {
                    onColorChanged.invoke(
                        Color(
                            it,
                            rgb.green,
                            rgb.blue,
                        ),
                    )
                },
            )
            Slider(
                modifier = Modifier.width(min(400.dp, width)),
                value = rgb.green,
                onValueChange = {
                    onColorChanged.invoke(
                        Color(
                            rgb.red,
                            it,
                            rgb.blue,
                        ),
                    )
                },
            )
            Slider(
                modifier = Modifier.width(min(400.dp, width)),
                value = rgb.blue,
                onValueChange = {
                    onColorChanged.invoke(
                        Color(
                            rgb.red,
                            rgb.green,
                            it,
                        ),
                    )
                },
            )
        }
    }
}

fun Color.complementary() = Color(
    red = 1f - red,
    green = 1f - green,
    blue = 1f - blue,
)
