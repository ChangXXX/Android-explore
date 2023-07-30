package com.example.compositionlocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compositionlocal.ui.theme.CompositionLocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Greeting("안녕하세요. 텍스트문장일걸요")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card(
        modifier = Modifier.padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            for (i in 0 until 9) {
                Text(shiftRightText(name, name.length - i))
            }
        }
    }
}

private fun shiftRightText(text: String, moveCnt: Int): String {
    var newText = StringBuilder(text.substring(0, moveCnt))
    newText.insert(0, text.substring(moveCnt))

    return newText.toString()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompositionLocalTheme {
        Greeting("안녕하세요. 텍스트문장일걸요")
    }
}
