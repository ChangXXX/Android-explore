package team.study.composemovieapp.library.storage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.study.composemovieapp.library.storage.IStorage
import team.study.composemovieapp.library.storage.StorageManager
import team.study.composemovieapp.library.storage.helpers.DataConverter
import team.study.composemovieapp.library.storage.helpers.DataEncoding
import team.study.composemovieapp.library.storage.prefs.SharedPrefsStorageProvider
import team.study.composemovieapp.library.storage.prefs.StorageProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun bindOnDiscStorage(
        storage: StorageProvider,
        converter: DataConverter,
        encoding: DataEncoding,
    ): IStorage = StorageManager(storage, converter, encoding)

    @Provides
    fun provideSharePrefStorageProvider(provider: SharedPrefsStorageProvider): StorageProvider =
        provider

}