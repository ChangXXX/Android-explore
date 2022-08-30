package com.study.room.data.folderandfile.repository

import com.study.room.data.folderandfile.db.FileEntity
import com.study.room.data.folderandfile.db.FolderEntity
import com.study.room.data.folderandfile.folder.db.FolderAndFiles
import kotlinx.coroutines.flow.Flow

interface FolderFileRepository {

    suspend fun addFolder(folder: FolderEntity)

    fun getFolders(): Flow<List<FolderEntity>>

    suspend fun addFile(file: FileEntity)

    fun getFiles(): Flow<List<FileEntity>>

    fun getFolderAndFiles(): Flow<List<FolderAndFiles>>
}
