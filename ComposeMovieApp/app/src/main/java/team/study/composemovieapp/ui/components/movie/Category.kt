package team.study.composemovieapp.ui.components.movie

import MovieItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.study.composemovieapp.ui.theme.ComposeMovieAppTheme
import team.study.composemovieapp.ui.theme.Paddings

@Composable
fun CategoryRow() {
    Column(

    ) {
        CategoryTitle("Action")
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 10.dp
            )
        ) {
//            itemsIndexed
            item {
                MovieItem()
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

@Preview
@Composable
fun CategoryRowPreview() {
    ComposeMovieAppTheme {
        CategoryRow()
    }
}