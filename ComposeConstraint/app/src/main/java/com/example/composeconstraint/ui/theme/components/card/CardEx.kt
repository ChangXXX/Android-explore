package com.example.composeconstraint.ui.theme.components.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.composeconstraint.CardData
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme

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
