package com.study.moviesearch.data.movie.repository

import com.study.moviesearch.data.movie.data.Movie

interface MovieRepository {

    suspend fun getMovies(query: String): Result<List<Movie>>
}
