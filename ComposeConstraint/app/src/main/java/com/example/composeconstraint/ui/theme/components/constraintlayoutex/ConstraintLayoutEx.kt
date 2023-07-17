package com.example.composeconstraint.ui.theme.components.constraintlayoutex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

@Composable
fun ConstraintLayoutEx() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val (redBox, magentaBox, yellowBox, text) = createRefs()
        val boxs = arrayOf(redBox, yellowBox, magentaBox)
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top, margin = 18.dp)
                },
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(parent.top, margin = 26.dp)
                },
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    top.linkTo(parent.top, margin = 34.dp)
                },
        )

        createHorizontalChain(
            elements = boxs,
            chainStyle = ChainStyle.SpreadInside,
        )
        val barrier = createBottomBarrier(
            elements = boxs,
            margin = 10.dp,
        )
        Text(
            text = "안녕하세요 텍스트인데요 배리어가 잘 동작하나 보려고 적어봤어요 이게 맞나요? 아무튼 이건 텍스트에요",
            modifier = Modifier
                .width(40.dp)
                .constrainAs(text) {
                    top.linkTo(barrier)
                    start.linkTo(yellowBox.start)
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutExPreview() {
    ComposeConstraintTheme {
        ConstraintLayoutEx()
    }
}
