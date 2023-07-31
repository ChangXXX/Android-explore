package com.example.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    MyNav()
                }
            }
        }
    }
}

@Composable
fun MyNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController, "Home", modifier = modifier) {
        composable("Home") {
            Column {
                Text("Home")
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Go to Playground")
                }
                Button(onClick = {
                    navController.navigate("Office") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Go to Office")
                }
                Button(onClick = {
                    navController.navigate("Argument/changXXX") {
                        launchSingleTop = true
                    }
                }) {
                    Text("Id 전달하기")
                }
            }
        }
        composable("Office") {
            Column {
                Text("Office")
                Button(onClick = {
                    navController.navigate("Playground") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Go to Playground")
                }
                Button(onClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Go to Home")
                }
            }
        }
        composable("Playground") {
            Column {
                Text("Playground")
                Button(onClick = {
                    navController.navigate("Office") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Go to Office")
                }
                Button(onClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }) {
                    Text("Go to Home")
                }
            }
        }

        composable("Argument/{userId}") { backStackEntry ->
            backStackEntry.arguments?.getString("userId")?.let { id ->
                Text("userId : $id")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeNavigationTheme {
        MyNav()
    }
}
