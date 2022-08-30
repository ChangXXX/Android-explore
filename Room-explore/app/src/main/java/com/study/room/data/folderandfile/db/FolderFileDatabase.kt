package com.study.room.data.folderandfile.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FolderEntity::class, FileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FolderFileDatabase : RoomDatabase() {
    abstract fun folderFileDao(): FolderFileDao

    companion object {
        const val FOLDER_FILE_DATABASE = "folder_file_database"
    }
}
