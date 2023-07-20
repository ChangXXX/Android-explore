package com.example.composesimpletodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TodoViewModel : ViewModel() {
    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    private val _rawTodoList = mutableListOf<TodoData>()
    private val _todoList = MutableLiveData<List<TodoData>>(_rawTodoList)
    val todoList: LiveData<List<TodoData>> = _todoList
    private val keyIncrement = MutableStateFlow(0)

    val setText: (String) -> Unit = {
        _text.value = it
    }

    val onSubmit: (String) -> Unit = {
        _rawTodoList.add(TodoData(keyIncrement.value++, it))
        _todoList.value = _rawTodoList.toMutableList()
        _text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, text ->
        val idx = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList[idx] = _rawTodoList[idx].copy(text = text)
        _todoList.value = _rawTodoList.toMutableList()
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val idx = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList[idx] = _rawTodoList[idx].copy(done = checked)
        _todoList.value = _rawTodoList.toMutableList()
    }

    val onDelete: (Int) -> Unit = { key ->
        val idx = _rawTodoList.indexOfFirst { it.key == key }
        _rawTodoList.removeAt(idx)
        _todoList.value = _rawTodoList.toMutableList()
    }
}
