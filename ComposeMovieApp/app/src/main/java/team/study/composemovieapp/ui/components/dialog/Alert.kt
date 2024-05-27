package team.study.composemovieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import team.study.composemovieapp.ui.models.dialog.DialogButton
import team.study.composemovieapp.ui.models.dialog.DialogContent
import team.study.composemovieapp.ui.models.dialog.DialogText
import team.study.composemovieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Alert(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>,
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Header(title),
        dialogContent = DialogContent.Large(
            DialogText.Default(
                bodyText
            )
        ),
        buttons = buttons
    )
}