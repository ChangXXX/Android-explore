package com.example.composeconstraint.ui.theme.components.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun CanvasEx() {
    Canvas(modifier = Modifier.size(20.dp)) {
        drawLine(Color.Red, Offset(35f, 10f), Offset(20f, 20f))
        drawCircle(Color.Yellow, 10f, Offset(35f, 20f))
        drawRect(Color.Magenta, Offset(10f, 25f), Size(10f, 10f))

        /**
         * materialPath {
         moveTo(2.01f, 21.0f)
         lineTo(23.0f, 12.0f)
         lineTo(2.01f, 3.0f)
         lineTo(2.0f, 10.0f)
         lineToRelative(15.0f, 2.0f)
         lineToRelative(-15.0f, 2.0f)
         close()
         }
         */
        drawLine(Color.Green, Offset(2f, 21f), Offset(23f, 12f))
        drawLine(Color.Green, Offset(23f, 12f), Offset(2f, 3f))
        drawLine(Color.Green, Offset(2f, 3f), Offset(2f, 10f))
        drawLine(Color.Green, Offset(2f, 10f), Offset(17f, 12f))
        drawLine(Color.Green, Offset(17f, 12f), Offset(2f, 14f))
        drawLine(Color.Green, Offset(2f, 14f), Offset(2f, 21f))
    }
}

@Preview(showBackground = true)
@Composable
fun CanvasExPreview() {
    ComposeConstraintTheme {
        CanvasEx()
    }
}
