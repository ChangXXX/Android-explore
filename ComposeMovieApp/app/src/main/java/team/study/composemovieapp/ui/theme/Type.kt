package team.study.composemovieapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import team.study.composemovieapp.R

private val spoqaBold = FontFamily(
    Font(R.font.spoqa_han_sans_neo_bold, FontWeight.Bold)
)

private val spoqaRegular = FontFamily(
    Font(R.font.spoqa_han_sans_neo_regular, FontWeight.Normal)
)

private val spoqaThin = FontFamily(
    Font(R.font.spoqa_han_sans_neo_thin, FontWeight.Thin)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 60.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 32.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 24.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = spoqaThin,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 18.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 15.sp
    ),
    labelLarge = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 18.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 18.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = spoqaBold,
        fontSize = 15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 15.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = spoqaRegular,
        fontSize = 14.sp
    )
)

val Typography.headlineMediumTitle: TextStyle
    @Composable get() = headlineMedium.copy(
        fontSize = 24.sp
    )

val Typography.dialogLabelLarge: TextStyle
    @Composable get() = labelLarge.copy(
        fontSize = 18.sp
    )

val Typography.underlinedDialogLabelLarge: TextStyle
    @Composable get() = labelLarge.copy(
        textDecoration = TextDecoration.Underline
    )

val Typography.underlinedLabelLarge: TextStyle
    @Composable get() = labelLarge.copy(
        textDecoration = TextDecoration.Underline
    )
