package com.example.composeconstraint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme
import com.example.composeconstraint.ui.theme.components.canvas.CanvasEx
import com.example.composeconstraint.ui.theme.components.card.CardEx
import com.example.composeconstraint.ui.theme.components.constraintlayoutex.ConstraintLayoutEx
import com.example.composeconstraint.ui.theme.components.dialog.DialogEx

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeConstraintTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(
                            state = rememberScrollState(),
                            enabled = true,
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ConstraintLayoutEx()
                    CardEx(
                        CardData(
                            "",
                            "hi??",
                            "작가입니다",
                            "글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요, 글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요",
                        ),
                    )
                    CardEx(
                        CardData(
                            "",
                            "hi??",
                            "작가입니다22",
                            "글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요, 글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요",
                        ),
                    )
                    CanvasEx()
                    DialogEx()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeConstraintTheme {
        ConstraintLayoutEx()
        CardEx(
            CardData(
                "",
                "hi??",
                "작가입니다",
                "글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요, 글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요",
            ),
        )
        CanvasEx()
        DialogEx()
    }
}
