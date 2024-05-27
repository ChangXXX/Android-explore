package team.study.composemovieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import team.study.composemovieapp.R
import team.study.composemovieapp.ui.models.buttons.LeadingIconData
import team.study.composemovieapp.ui.models.dialog.DialogButton
import team.study.composemovieapp.ui.theme.ComposeMovieAppTheme

@Preview
@Composable
fun AlertPreview() {
    ComposeMovieAppTheme {
        DialogPopup.Alert(
            title = "title",
            bodyText = "body body body ",
            buttons = listOf(
                DialogButton.UnderlinedText("Okay") {}
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    ComposeMovieAppTheme {
        DialogPopup.Default(
            title = "title",
            bodyText = "body body body ",
            buttons = listOf(
                DialogButton.Primary("Okay") {},
                DialogButton.SecondaryBorderless("Cancel") {}
            )
        )
    }
}

@Preview
@Composable
fun RatingPreview() {
    ComposeMovieAppTheme {
        DialogPopup.Rating(
            movieName = "반지의 제왕",
            rating = 7.5f,
            buttons = listOf(
                DialogButton.Primary(
                    title = "Okay",
                    leadingIconData = LeadingIconData(
                        iconDrawable = R.drawable.ic_send,
                        iconContentDescription = null
                    )
                ) {},
                DialogButton.SecondaryBorderless("Cancel") {}
            )
        )
    }
}