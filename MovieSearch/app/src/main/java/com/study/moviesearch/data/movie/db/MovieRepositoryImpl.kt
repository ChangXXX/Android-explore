package com.study.moviesearch.data.movie.db

import com.study.moviesearch.data.movie.api.MovieService
import com.study.moviesearch.data.movie.data.Movie
import com.study.moviesearch.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getMovies(query: String): Result<List<Movie>> {
        val result = movieService.getMovies(
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET,
            Constants.MOVIES,
            query
        )

        if (!result.isSuccessful) {
            return Result.failure(Exception(result.code().toString()))
        }
        return Result.success(result.body()!!.items.toList())
    }
}
