package com.study.room.ui.folder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.room.data.folderandfile.db.FolderEntity
import com.study.room.data.folderandfile.repository.FolderFileRepository
import com.study.room.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(
    private val repository: FolderFileRepository
) : ViewModel() {

    private val _eventFlow: MutableSharedFlow<Event> =
        MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _folderUiState: MutableStateFlow<UiState<List<FolderEntity>>> =
        MutableStateFlow(UiState.Loading)
    val folderUiState = _folderUiState.asStateFlow()

    fun getFolders() {
        viewModelScope.launch {
            repository.getFolders()
                .catch { exception ->
                    _folderUiState.emit(UiState.Error(exception))
                }
                .collect { folders ->
                    Log.d("FOLDERVIEWMODEL", "items: $folders")
                    _folderUiState.emit(UiState.Success(folders))
                }
        }
    }

    fun addFolder() {
        event(Event.addFolder)
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}
