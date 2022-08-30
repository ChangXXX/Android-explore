package com.study.room.data.folderandfile.db

import androidx.room.*
import com.study.room.data.folderandfile.folder.db.FolderAndFiles
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderFileDao {

    /**
     * folderDao
     */
    @Query("SELECT * FROM folders")
    fun getFolders(): Flow<List<FolderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFolder(entity: FolderEntity)

    /**
     * fileDao
     */
    @Query("SELECT * FROM files")
    fun getFiles(): Flow<List<FileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFile(entity: FileEntity)

    /**
     * folderAndFileDao
     */
    @Transaction
    @Query("SELECT * FROM folders")
    fun getFolderAndFiles(): Flow<List<FolderAndFiles>>
}
