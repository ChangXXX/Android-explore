package com.study.room.ui.file

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.room.data.folderandfile.db.FileEntity
import com.study.room.data.folderandfile.repository.FolderFileRepository
import com.study.room.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FileViewModel @Inject constructor(
    private val repository: FolderFileRepository
) : ViewModel() {

    private val _addEventFlow: MutableSharedFlow<Unit> =
        MutableSharedFlow()
    val addEventFlow = _addEventFlow.asSharedFlow()

    private val _fileUiState: MutableStateFlow<UiState<List<FileEntity>>> =
        MutableStateFlow(UiState.Loading)
    val fileUiState = _fileUiState.asStateFlow()

    fun getFiles(folderId: Int) {
        viewModelScope.launch {
            repository.getFolderAndFiles()
                .catch { exception ->
                    _fileUiState.emit(UiState.Error(exception))
                }.collect { folderAndFiles ->
                    Log.d("FILEVIEWMODEL", "items : $folderAndFiles")
                    for (element in folderAndFiles) {
                        if (element.folder.id == folderId) {
                            _fileUiState.emit(UiState.Success(element.files))
                            break
                        }
                    }
                }
        }
    }

    fun addFile() {
        viewModelScope.launch {
            _addEventFlow.emit(Unit)
        }
    }

    fun addFile(title: String, parentId: Int) {
        viewModelScope.launch {
            repository.addFile(FileEntity(title, parentId))
        }
    }
}
