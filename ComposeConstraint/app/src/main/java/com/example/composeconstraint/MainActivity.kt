package com.example.composeconstraint

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeconstraint.ui.theme.ComposeConstraintTheme
import com.example.composeconstraint.ui.theme.components.canvas.CanvasEx
import com.example.composeconstraint.ui.theme.components.card.CardEx
import com.example.composeconstraint.ui.theme.components.constraintlayoutex.ConstraintLayoutEx
import com.example.composeconstraint.ui.theme.components.customdialog.CustomDialog
import com.example.composeconstraint.ui.theme.components.dialog.DialogEx
import com.example.composeconstraint.ui.theme.components.dropdown.DropDownEx
import com.example.composeconstraint.ui.theme.components.room.RoomEx
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeConstraintTheme {
                var counter by remember { mutableStateOf(0) }
                val coroutineScope = rememberCoroutineScope()
                val scaffoldState = rememberScaffoldState()
                var pyeong by rememberSaveable {
                    mutableStateOf("23")
                }
                var squareMeter by remember {
                    mutableStateOf((pyeong.toInt() * 3.306).toString())
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("그냥 이것저것")
                            },
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            Text("HI??")
                            Button(onClick = {
                                counter++
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(message = "바텀앱바 메세지")
                                }
                            }) {
                                Text("바텀 스낵바 버튼")
                            }
                        }
                    },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .verticalScroll(state = rememberScrollState()),
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
                        CustomDialog()
                        DropDownEx()
                        Button(
                            onClick = {
                                counter++
                                coroutineScope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "카운터에요 $counter",
                                        actionLabel = "닫기",
                                        duration = SnackbarDuration.Short,
                                    )
                                    when (result) {
                                        SnackbarResult.Dismissed -> {
                                            Log.d("SnackbarResult", "Dismissed")
                                        }
                                        SnackbarResult.ActionPerformed -> {
                                            Log.d("SnackbarResult", "ActionPerformed")
                                        }
                                    }
                                }
                            },
                            modifier = Modifier.width(200.dp)
                                .height(50.dp),
                        ) {
                            Text("더하기 $counter")
                        }
                        RoomEx(
                            pyeong,
                            squareMeter,
                        ) {
                            if (it.isBlank()) {
                                pyeong = ""
                                squareMeter = ""
                                return@RoomEx
                            }
                            val numeric = it.toFloatOrNull() ?: return@RoomEx
                            pyeong = it
                            squareMeter = (numeric * 3.306).toString()
                        }
                    }
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
        CustomDialog()
        DropDownEx()
        RoomEx("23", "${23 * 3.306}", {})
    }
}
