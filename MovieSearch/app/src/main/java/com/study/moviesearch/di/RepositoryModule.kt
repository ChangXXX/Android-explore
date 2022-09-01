package com.study.moviesearch.di

import com.study.moviesearch.data.movie.api.MovieService
import com.study.moviesearch.data.movie.db.MovieRepository
import com.study.moviesearch.data.movie.db.MovieRepositoryImpl
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
}
