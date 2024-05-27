package team.study.composemovieapp.ui.models.dialog

import team.study.composemovieapp.ui.models.buttons.LeadingIconData

sealed class DialogButton(
    open val title: String,
    open val action: (() -> Unit)?,
) {
    data class Primary(
        override val title: String,
        val leadingIconData: LeadingIconData? = null,
        override val action: (() -> Unit)? = null,
    ) : DialogButton(
        title = title,
        action = action
    )

    data class Secondary(
        override val title: String,
        override val action: (() -> Unit)? = null,
    ) : DialogButton(
        title = title,
        action = action
    )

    data class UnderlinedText(
        override val title: String,
        override val action: (() -> Unit)? = null,
    ) : DialogButton(
        title = title,
        action = action
    )

    data class SecondaryBorderless(
        override val title: String,
        override val action: (() -> Unit)? = null,
    ) : DialogButton(
        title = title,
        action = action
    )
}
