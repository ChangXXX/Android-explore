package com.example.composesimpletodo

data class TodoData(
    val key: Int,
    val text: String,
    val done: Boolean = false,
)
