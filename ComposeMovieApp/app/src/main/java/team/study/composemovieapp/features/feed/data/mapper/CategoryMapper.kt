package team.study.composemovieapp.features.feed.data.mapper

import team.study.composemovieapp.features.common.entity.CategoryEntity
import team.study.composemovieapp.features.common.entity.EntityWrapper
import team.study.composemovieapp.features.common.entity.MovieDetailEntity
import team.study.composemovieapp.features.common.mapper.BaseMapper
import team.study.composemovieapp.features.common.network.model.MovieResponse
import team.study.composemovieapp.features.feed.data.FeedConstants
import team.study.composemovieapp.features.feed.domain.SortOrder
import team.study.composemovieapp.library.storage.IStorage
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: IStorage
) : BaseMapper<List<MovieResponse>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<MovieResponse>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<MovieDetailEntity>()
                    .apply {
                        addAll(model.map { it.toMovieDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.MOVIE_LIST_KEY, it)
                    }
                    .map {
                        it.toMovieThumbnailEntity()
                    }
                    .toCategoryList(if (extra is SortOrder) extra else SortOrder.RATING)
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}
