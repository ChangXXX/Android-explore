package com.example.composeconstraint.ui.theme.components.room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun RoomEx(
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = { onPyeongChange.invoke(it) },
            label = {
                Text("평")
            },
        )
        OutlinedTextField(
            value = squareMeter,
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
        RoomEx("23", "${23 * 3.306}", {})
    }
}
