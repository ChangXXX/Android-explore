package com.study.room.di

import com.study.room.data.folderandfile.db.FolderFileDao
import com.study.room.data.folderandfile.repository.FolderFileRepository
import com.study.room.data.folderandfile.repository.FolderFileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideFolderFileRepository(dao: FolderFileDao): FolderFileRepository =
        FolderFileRepositoryImpl(dao)
}
