package com.example.composecolorpicker

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val _colorState = MutableStateFlow(Color.Magenta)
    val colorState = _colorState.asStateFlow()

    fun changeColor(color: Color) {
        viewModelScope.launch {
            _colorState.update { color }
        }
    }
}
