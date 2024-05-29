package team.study.composemovieapp.features.common.repository

import team.study.composemovieapp.features.common.network.api.IMovieAppNetworkApi
import team.study.composemovieapp.library.network.model.ApiResponse
import timber.log.Timber
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi
): IMovieRepository {
    override suspend fun getMovieList() {
        val data = movieNetworkApi.getMovies().response

        when (data) {
            is ApiResponse.Success -> {
                val movieLst = data.data
            }
            is ApiResponse.Fail -> {

            }
        }
    }
}