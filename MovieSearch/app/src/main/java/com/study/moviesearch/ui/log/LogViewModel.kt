package com.study.moviesearch.ui.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.moviesearch.data.log.db.LogEntity
import com.study.moviesearch.data.log.repository.LogRepository
import com.study.moviesearch.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogViewModel @Inject constructor(
    private val repository: LogRepository
) : ViewModel() {

    private val _logStateFlow: MutableStateFlow<UiState<List<LogEntity>>> =
        MutableStateFlow(UiState.Loading)
    val logStateFlow = _logStateFlow.asStateFlow()

    /**
     * log 데이터 갱신
     * @return 성공 : uiState 에 List<Log> 전달
     */
    fun getLogs() {
        viewModelScope.launch {
            _logStateFlow.emit(UiState.Loading)

            val logs = repository.getLogs()
            if (logs.isEmpty()) _logStateFlow.emit(UiState.Empty)
            else _logStateFlow.emit(UiState.Success(logs))
        }
    }
}
