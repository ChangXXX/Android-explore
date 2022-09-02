package com.study.moviesearch.di

import android.content.Context
import androidx.room.Room
import com.study.moviesearch.data.log.db.LogDao
import com.study.moviesearch.data.log.db.LogDatabase
import com.study.moviesearch.utils.Constants.LOG_DATABASE
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
    fun provideRoomDatabase(@ApplicationContext context: Context): LogDatabase {
        return synchronized(this) {
            Room.databaseBuilder(
                context,
                LogDatabase::class.java,
                LOG_DATABASE
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideLogDao(database: LogDatabase): LogDao = database.logDao()
}
