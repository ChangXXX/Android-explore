package com.example.composesimpletodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composesimpletodo.ui.theme.ComposeSimpleTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSimpleTodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    TopLevel()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSimpleTodoTheme {
        TopLevel()
    }
}

@Composable
fun TopLevel(viewModel: TodoViewModel = viewModel()) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            TodoInput(
                text = viewModel.text.observeAsState("").value,
                onTextChanged = viewModel.setText,
                onSubmit = viewModel.onSubmit,
            )

            val todos = viewModel.todoList.observeAsState(emptyList()).value
            LazyColumn {
                items(
                    items = todos,
                    key = { item ->
                        item.key
                    },
                ) { todoData ->
                    Todo(
                        todoData = todoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete,
                    )
                }
            }
        }
    }
}

@Composable
fun TodoInput(
    text: String,
    onTextChanged: (String) -> Unit,
    onSubmit: (String) -> Unit,
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text(text = "입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoInputPreview() {
    ComposeSimpleTodoTheme {
        TodoInput("테스트", {}, {})
    }
}

@Composable
fun Todo(
    todoData: TodoData,
    onEdit: (Key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {},
) {
    var isEditing by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
    ) {
        Crossfade(targetState = isEditing) {
            when (it) {
                true -> {
                    var (newText, setNewText) = remember { mutableStateOf(todoData.text) }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp),
                    ) {
                        OutlinedTextField(
                            value = newText,
                            onValueChange = setNewText,
                            modifier = Modifier.weight(1f),
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = {
                            onEdit(todoData.key, newText)
                            isEditing = false
                        }) {
                            Text("완료")
                        }
                    }
                }
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            text = todoData.text,
                            modifier = Modifier.weight(1f),
                        )
                        Text(
                            text = "완료",
                        )
                        Checkbox(
                            checked = todoData.done,
                            onCheckedChange = { checked ->
                                onToggle(todoData.key, checked)
                            },
                        )
                        Button(onClick = {
                            isEditing = true
                        }) {
                            Text(text = "수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = {
                            onDelete(todoData.key)
                        }) {
                            Text(text = "삭제")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoPreview() {
    ComposeSimpleTodoTheme {
        Todo(TodoData(0, "테스트"), { _, _ -> }, { _, _ -> }, {})
    }
}
