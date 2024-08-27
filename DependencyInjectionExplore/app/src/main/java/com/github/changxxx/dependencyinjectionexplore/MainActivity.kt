package com.github.changxxx.dependencyinjectionexplore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.changxxx.dependencyinjectionexplore.ui.theme.DependencyInjectionExploreTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var unScopeDiTest: UnScopeDiTest

    @Inject
    lateinit var scopeDiTest: ScopeDiTest

//    @Inject
    lateinit var foo: Foo
//
//    @Inject
//    lateinit var bar: Bar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        assert(::foo.isInitialized)
//        assert(::bar.isInitialized)
//        assert(foo.bar != null)

        setContent {
            DependencyInjectionExploreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        Log.e(TAG, "activity ScopeDiTest :: $scopeDiTest")
        Log.e(TAG, "activity UnScopeDiTest :: $unScopeDiTest")

    }

    @Inject
    fun injectFoo(foo: Foo) {
        this.foo = foo
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DependencyInjectionExploreTheme {
        Greeting("Android")
    }
}