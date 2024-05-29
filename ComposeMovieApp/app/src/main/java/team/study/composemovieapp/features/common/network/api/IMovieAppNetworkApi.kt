package team.study.composemovieapp.features.common.network.api

import team.study.composemovieapp.features.common.network.model.MovieResponse
import team.study.composemovieapp.library.network.model.ApiResult

interface IMovieAppNetworkApi {
    suspend fun getMovies(): ApiResult<List<MovieResponse>>
}