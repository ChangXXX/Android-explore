package com.example.composeconstraint.ui.theme.components.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun DropDownEx() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { expandDropDownMenu = true }) {
            Text("드롭다운이 열릴거에요")
        }
        Text("카운터 : $counter")
    }

    DropdownMenu(
        expanded = expandDropDownMenu,
        onDismissRequest = { expandDropDownMenu = false },
    ) {
        DropdownMenuItem(onClick = { counter++ }) {
            Text("증가할거에요")
        }
        DropdownMenuItem(onClick = { counter-- }) {
            Text("감소할거에요")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownPreview() {
    ComposeConstraintTheme {
        DropDownEx()
    }
}
