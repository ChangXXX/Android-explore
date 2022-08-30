package com.study.room.di

import android.content.Context
import androidx.room.Room
import com.study.room.data.folderandfile.db.FolderFileDao
import com.study.room.data.folderandfile.db.FolderFileDatabase
import com.study.room.data.folderandfile.db.FolderFileDatabase.Companion.FOLDER_FILE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFolderAndFileDatabase(
        @ApplicationContext context: Context
    ): FolderFileDatabase {
        return Room.databaseBuilder(
            context,
            FolderFileDatabase::class.java,
            FOLDER_FILE_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideFolderFileDao(database: FolderFileDatabase): FolderFileDao = database.folderFileDao()
}
