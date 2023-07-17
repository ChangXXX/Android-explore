package com.example.composeconstraint.ui.theme.components.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationEx() {
    var helloWorldVisible by remember { mutableStateOf(false) }
    var isRed by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(targetValue = if (isRed) Color.Red else Color.LightGray)

    Column(
        modifier = Modifier.fillMaxSize()
            .height(300.dp)
            .padding(16.dp)
            .background(backgroundColor),
    ) {
        AnimatedVisibility(
            visible = helloWorldVisible,
            enter = expandVertically() + slideInHorizontally(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Text("Hello world visible")
        }
        RadioButtonRowEx(selected = helloWorldVisible, text = "show") { helloWorldVisible = true }
        RadioButtonRowEx(selected = !helloWorldVisible, text = "hide") { helloWorldVisible = false }
        Text("change background color")
        RadioButtonRowEx(selected = !isRed, text = "LightGrey") { isRed = false }
        RadioButtonRowEx(selected = isRed, text = "Red") { isRed = true }
    }
}

@Composable
fun RadioButtonRowEx(selected: Boolean, text: String, onClick: () -> Unit) {
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
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationPreview() {
    ComposeConstraintTheme {
        AnimationEx()
    }
}
