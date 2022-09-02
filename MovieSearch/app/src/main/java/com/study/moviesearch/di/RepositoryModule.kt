package com.study.moviesearch.di

import com.study.moviesearch.data.log.db.LogDao
import com.study.moviesearch.data.log.repository.LogRepository
import com.study.moviesearch.data.log.repository.LogRepositoryImpl
import com.study.moviesearch.data.movie.api.MovieService
import com.study.moviesearch.data.movie.repository.MovieRepository
import com.study.moviesearch.data.movie.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepository(movieService: MovieService): MovieRepository =
        MovieRepositoryImpl(movieService)

    @Provides
    fun provideLogRepository(logDao: LogDao): LogRepository =
        LogRepositoryImpl(logDao)
}
