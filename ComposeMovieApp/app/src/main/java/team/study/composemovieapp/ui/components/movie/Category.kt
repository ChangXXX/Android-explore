package team.study.composemovieapp.ui.components.movie

import MovieItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import team.study.composemovieapp.features.common.entity.CategoryEntity
import team.study.composemovieapp.features.feed.presentation.input.IFeedViewModelInput
import team.study.composemovieapp.ui.theme.Paddings

@Composable
fun CategoryRow(
    categoryEntity: CategoryEntity,
    input: IFeedViewModelInput,
) {
    Column(

    ) {
        CategoryTitle(categoryEntity.genre)
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = Paddings.large
            )
        ) {
//            itemsIndexed
            itemsIndexed(categoryEntity.movieFeedEntities) { _, item ->
                MovieItem(
                    movie = item,
                    input = input
                )
            }
        }
    }
}

@Composable
fun CategoryTitle(titleName: String) {
    Text(
        text = titleName,
        modifier = Modifier
            .padding(
                vertical = Paddings.large,
                horizontal = Paddings.extra
            ),
        style = MaterialTheme.typography.headlineMedium
    )
}