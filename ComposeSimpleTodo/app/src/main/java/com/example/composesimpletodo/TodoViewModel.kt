package com.example.composesimpletodo

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TodoViewModel : ViewModel() {
    val text = mutableStateOf("")
    val todoList = mutableStateListOf<TodoData>()
    private val keyIncrement = MutableStateFlow(0)

    val onSubmit: (String) -> Unit = {
        todoList.add(TodoData(keyIncrement.value++, it))
        text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, text ->
        val idx = todoList.indexOfFirst { it.key == key }
        todoList[idx] = todoList[idx].copy(text = text)
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val idx = todoList.indexOfFirst { it.key == key }
        todoList[idx] = todoList[idx].copy(done = checked)
    }

    val onDelete: (Int) -> Unit = { key ->
        val idx = todoList.indexOfFirst { it.key == key }
        todoList.removeAt(idx)
    }
}
