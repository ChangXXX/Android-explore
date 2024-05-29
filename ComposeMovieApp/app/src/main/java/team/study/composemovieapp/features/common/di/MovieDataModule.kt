package team.study.composemovieapp.features.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.study.composemovieapp.features.common.network.api.IMovieAppNetworkApi
import team.study.composemovieapp.features.common.network.api.MovieAppNetworkApi
import team.study.composemovieapp.features.common.repository.IMovieRepository
import team.study.composemovieapp.features.common.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDataModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Singleton
    @Binds
    abstract fun bindNetwork(networkApi: MovieAppNetworkApi): IMovieAppNetworkApi
}