package com.study.room.ui.folder.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.room.data.folderandfile.db.FolderEntity
import com.study.room.data.folderandfile.repository.FolderFileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderAddViewModel @Inject constructor(
    private val repository: FolderFileRepository
) : ViewModel() {

    private val _createFlow = MutableSharedFlow<Unit>()
    val createFlow = _createFlow.asSharedFlow()

    fun create() {
        viewModelScope.launch {
            _createFlow.emit(Unit)
        }
    }

    fun addFolder(title: String) {
        viewModelScope.launch {
            repository.addFolder(FolderEntity(title))
        }
    }
}
