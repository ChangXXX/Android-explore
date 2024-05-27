package team.study.composemovieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import team.study.composemovieapp.ui.models.dialog.DialogButton
import team.study.composemovieapp.ui.models.dialog.DialogContent
import team.study.composemovieapp.ui.models.dialog.DialogText
import team.study.composemovieapp.ui.models.dialog.DialogTitle

object DialogPopup

@Composable
fun DialogPopup.Default(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Default(title),
        dialogContent = DialogContent.Default(
            DialogText.Default(bodyText)
        ),
        buttons = buttons
    )
}