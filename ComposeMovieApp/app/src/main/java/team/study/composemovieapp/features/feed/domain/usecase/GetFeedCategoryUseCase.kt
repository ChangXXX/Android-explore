package team.study.composemovieapp.features.feed.domain.usecase

import team.study.composemovieapp.features.common.entity.CategoryEntity
import team.study.composemovieapp.features.common.entity.EntityWrapper
import team.study.composemovieapp.features.common.repository.IMovieRepository
import team.study.composemovieapp.features.feed.domain.enums.SortOrder
import javax.inject.Inject

class GetFeedCategoryUseCase @Inject constructor(
    private val movieRepository: IMovieRepository,
) : IGetFeedCategoryUseCase {
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return movieRepository.getCategories(sortOrder)
    }
}