package team.study.composemovieapp.features.common.network.api

import com.google.gson.reflect.TypeToken
import team.study.composemovieapp.features.common.network.model.MovieResponse
import team.study.composemovieapp.library.network.retrofit.NetworkRequestFactory
import team.study.composemovieapp.library.network.model.ApiResult
import javax.inject.Inject

class MovieAppNetworkApi @Inject constructor(
    private val networkRequestFactory: NetworkRequestFactory,
) : IMovieAppNetworkApi {
    override suspend fun getMovies(): ApiResult<List<MovieResponse>> {
        return networkRequestFactory.create(
            url = "top250.json",
            type = object : TypeToken<List<MovieResponse>>(){}.type
        )
    }
}