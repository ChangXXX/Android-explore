package team.study.composemovieapp.features.common.repository

interface IMovieRepository {
    suspend fun getMovieList()
}