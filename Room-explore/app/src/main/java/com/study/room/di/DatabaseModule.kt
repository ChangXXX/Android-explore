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
    /**
     *     @Provides
     @Singleton
     fun provideRoomDatabase(
     @ApplicationContext context: Context
     ): SearchDataBase {
     var INSTANCE: SearchDataBase? = null
     return INSTANCE ?: synchronized(this) {
     INSTANCE ?: Room.databaseBuilder(
     context,
     SearchDataBase::class.java,
     SEARCH_DATABASE
     ).fallbackToDestructiveMigration().build()
     }.also { INSTANCE = it }
     }
     */

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
