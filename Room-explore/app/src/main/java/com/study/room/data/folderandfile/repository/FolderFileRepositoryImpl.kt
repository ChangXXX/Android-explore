package com.study.room.data.folderandfile.repository

import com.study.room.data.folderandfile.db.FileEntity
import com.study.room.data.folderandfile.db.FolderEntity
import com.study.room.data.folderandfile.db.FolderFileDao
import com.study.room.data.folderandfile.folder.db.FolderAndFiles
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FolderFileRepositoryImpl @Inject constructor(
    private val dao: FolderFileDao
) : FolderFileRepository {

    override suspend fun addFolder(folder: FolderEntity) = dao.addFolder(folder)

    override fun getFolders(): Flow<List<FolderEntity>> = dao.getFolders()

    override suspend fun addFile(file: FileEntity) = dao.addFile(file)

    override fun getFiles(): Flow<List<FileEntity>> = dao.getFiles()

    override fun getFolderAndFiles(): Flow<List<FolderAndFiles>> = dao.getFolderAndFiles()
}
