package team.study.composemovieapp.features.common.repository

import team.study.composemovieapp.features.common.entity.CategoryEntity
import team.study.composemovieapp.features.common.entity.EntityWrapper
import team.study.composemovieapp.features.common.entity.MovieDetailEntity
import team.study.composemovieapp.features.common.network.api.IMovieAppNetworkApi
import team.study.composemovieapp.features.feed.data.FeedConstants
import team.study.composemovieapp.features.feed.data.mapper.CategoryMapper
import team.study.composemovieapp.features.feed.domain.enums.SortOrder
import team.study.composemovieapp.library.storage.IStorage
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkApi: IMovieAppNetworkApi,
    private val storage: IStorage,
    private val categoryMapper: CategoryMapper
): IMovieRepository {

    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkApi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()
    }
}