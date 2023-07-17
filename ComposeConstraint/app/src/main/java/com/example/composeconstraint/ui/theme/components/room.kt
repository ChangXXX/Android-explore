package com.example.composeconstraint.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun RoomEx() {
    // rememberSaveable 많이 쓰면 안좋음
    var pyeong by rememberSaveable {
        mutableStateOf("23")
    }
    var squaremeter by remember {
        mutableStateOf((23 * 3.306).toString())
    }

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = {
                if (it.isBlank()) {
                    pyeong = ""
                    squaremeter = ""
                    return@OutlinedTextField
                }
                val numeric = it.toFloatOrNull() ?: return@OutlinedTextField
                pyeong = it
                squaremeter = (numeric * 3.306).toString()
            },
            label = {
                Text("평")
            },
        )
        OutlinedTextField(
            value = squaremeter,
            onValueChange = {},
            label = {
                Text("제곱미터")
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoomPreview() {
    ComposeConstraintTheme {
        RoomEx()
    }
}
