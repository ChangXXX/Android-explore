package team.study.composemovieapp.ui.components.movie.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.study.composemovieapp.ui.theme.ComposeMovieAppTheme
import team.study.composemovieapp.ui.theme.Paddings
import team.study.composemovieapp.ui.theme.myColors

@Composable
fun SecondaryBorderlessButton(
    modifier: Modifier = Modifier,
    @StringRes textId: Int? = null,
    text: String = "",
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.myColors.disabledSecondary,
            disabledContentColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = textId?.let { stringResource(id = it) } ?: text,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(Paddings.small)
            )
        }
    }
}

@Preview
@Composable
fun SecondaryBorderlessButtonPreview() {
    ComposeMovieAppTheme {
        SecondaryBorderlessButton(text = "CANCEL") {
        }
    }
}