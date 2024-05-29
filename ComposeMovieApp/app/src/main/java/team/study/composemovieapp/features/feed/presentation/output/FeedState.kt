package team.study.composemovieapp.features.feed.presentation.output

import team.study.composemovieapp.features.common.entity.CategoryEntity

sealed class FeedState {

    object Loading: FeedState()
    class Main(
        val categories: List<CategoryEntity>
    ): FeedState()

    class Failed(
        val reason: String
    ): FeedState()
}