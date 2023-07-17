package com.example.composeconstraint.ui.theme.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun DialogEx() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = !openDialog }) {
            Text("다이얼로그에용")
        }
        Text("저는 카운터에요 : $counter")
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            confirmButton = {
                Button(onClick = {
                    counter++
                    openDialog = false
                }) {
                    Text("저는 카운터를 증가시켜요")
                }
            },
            dismissButton = {
                Button(onClick = {
                    openDialog = false
                }) {
                    Text("바이바이")
                }
            },
            title = {
                Text("저는 다이얼로그 제목이에요")
            },
            text = {
                Text("저는 다이얼로그 본문인데요 \n 바이바이를 누르지 말아주세요.")
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    ComposeConstraintTheme {
        DialogEx()
    }
}
