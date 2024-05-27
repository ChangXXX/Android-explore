package team.study.composemovieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import team.study.composemovieapp.R
import team.study.composemovieapp.ui.models.dialog.DialogButton
import team.study.composemovieapp.ui.models.dialog.DialogContent
import team.study.composemovieapp.ui.models.dialog.DialogText
import team.study.composemovieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    movieName: String,
    rating: Float,
    buttons: List<DialogButton>,
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("TITLE"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = movieName,
                rating = rating
            )
        ),
        buttons = buttons
    )
}