package com.example.themetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themetest.ui.theme.ThemeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Greeting("Android")
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
            Text(text = "Hello $name!")
            Button(onClick = { /*TODO*/ }) {
                Text("버튼")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemeTestTheme {
        Greeting("Android")
    }
}
