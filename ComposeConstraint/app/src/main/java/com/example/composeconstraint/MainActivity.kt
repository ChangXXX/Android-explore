package com.example.composeconstraint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

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
                }
            }
        }
    }
}

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

@Composable
fun CardEx(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(6.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        ) {
            val (profileImage, author, description) = createRefs()
            AsyncImage(
                model = cardData.imageUrl,
                contentDescription = cardData.imageDescription,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolderColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .constrainAs(profileImage) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start, margin = 8.dp)
                    },
            )
            Text(
                text = cardData.author,
                modifier = Modifier.constrainAs(author) {
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                },
            )
            Text(
                text = cardData.description,
                modifier = Modifier.constrainAs(description) {
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                },
                maxLines = 2,
            )
            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed)
            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
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
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutExPreview() {
    ComposeConstraintTheme {
        ConstraintLayoutEx()
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ComposeConstraintTheme() {
        CardEx(
            cardData = CardData(
                "",
                "hi??",
                "작가입니다",
                "글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요, 글의 내용인데 이게 길면 어떻게 되나 보려고 길게 한번 써보고 있어요",
            ),
        )
    }
}
