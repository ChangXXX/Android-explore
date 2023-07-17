package com.example.composeconstraint.ui.theme.components.customdialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun CustomDialog() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = !openDialog }) {
            Text("커스텀다이얼로그에용")
        }
        Text("저는 카운터에요 : $counter")
    }

    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "+1을 누르면 증가 \n -1을 누르면 감소")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Button(onClick = {
                            counter++
                            openDialog = false
                        }) {
                            Text("+1")
                        }
                        Button(onClick = {
                            counter--
                            openDialog = false
                        }) {
                            Text("-1")
                        }
                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text("취소")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDialogPreview() {
    ComposeConstraintTheme {
        CustomDialog()
    }
}
