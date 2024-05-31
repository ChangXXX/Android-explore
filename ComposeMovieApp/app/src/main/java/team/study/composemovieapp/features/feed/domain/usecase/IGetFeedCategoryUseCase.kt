package team.study.composemovieapp.features.feed.domain.usecase

import team.study.composemovieapp.features.common.entity.CategoryEntity
import team.study.composemovieapp.features.common.entity.EntityWrapper
import team.study.composemovieapp.features.feed.domain.enums.SortOrder

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
}