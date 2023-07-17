package com.example.composeconstraint.ui.theme.components.animation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun AnimationEx2() {
    var isDarkMode by remember { mutableStateOf(false) }

    val transition = updateTransition(targetState = isDarkMode, label = "다크 모드 애니메이션")
    val background by transition.animateColor(label = "다크모드 배경 색상 애니메이션") { state ->
        when (state) {
            true -> {
                Color.Black
            }
            false -> {
                Color.White
            }
        }
    }
    val textColor by transition.animateColor(label = "다크모드 텍스트 색상 애니메이션") { state ->
        when (state) {
            true -> {
                Color.White
            }
            false -> {
                Color.Black
            }
        }
    }

    val alpha by transition.animateFloat(label = "다크모드 배경 알파") { state ->
        when (state) {
            false -> {
                0.7f
            }
            true -> {
                1f
            }
        }
    }

    Column(
        modifier = Modifier
            .background(background)
            .alpha(alpha)
            .fillMaxWidth()
            .height(300.dp),
    ) {
        RadioButtonRowEx2(selected = !isDarkMode, text = "일반모드", color = textColor) {
            isDarkMode = false
        }
        RadioButtonRowEx2(selected = isDarkMode, text = "다크모드", color = textColor) {
            isDarkMode = true
        }
        Crossfade(targetState = isDarkMode) { state ->
            when (state) {
                false -> {
                    Column {
                        Box(
                            modifier = Modifier
                                .background(Color.Red)
                                .size(20.dp),
                        ) {
                            Text("a")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .size(20.dp),
                        ) {
                            Text("b")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Blue)
                                .size(20.dp),
                        ) {
                            Text("c")
                        }
                    }
                }
                true -> {
                    Row {
                        Box(
                            modifier = Modifier
                                .background(Color.Red)
                                .size(20.dp),
                        ) {
                            Text("1")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .size(20.dp),
                        ) {
                            Text("2")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Blue)
                                .size(20.dp),
                        ) {
                            Text("3")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RadioButtonRowEx2(
    selected: Boolean,
    text: String,
    color: Color = Color.Black,
    onClick: () -> Unit,
) {
    Row(
        Modifier.selectable(
            selected = selected,
            onClick = onClick,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )
        Text(text = text, color = color)
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationEx2Preview() {
    ComposeConstraintTheme {
        AnimationEx2()
    }
}
