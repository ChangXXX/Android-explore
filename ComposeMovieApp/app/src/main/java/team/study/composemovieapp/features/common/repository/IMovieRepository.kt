package team.study.composemovieapp.features.common.repository

import team.study.composemovieapp.features.common.entity.CategoryEntity
import team.study.composemovieapp.features.common.entity.EntityWrapper
import team.study.composemovieapp.features.common.entity.MovieDetailEntity
import team.study.composemovieapp.features.feed.domain.SortOrder

interface IMovieRepository {
    suspend fun getCategories(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}