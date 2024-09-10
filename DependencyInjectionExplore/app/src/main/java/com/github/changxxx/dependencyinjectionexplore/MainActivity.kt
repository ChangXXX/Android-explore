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
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var unScopeDiTest: UnScopeDiTest

    @Inject
    lateinit var scopeDiTest: ScopeDiTest

    @Inject
    lateinit var lazyFoo1: Lazy<Foo>

    @Inject
    lateinit var lazyFoo2: Lazy<Foo>


    @Named("Foo2")
    @Inject
    lateinit var foo2: Foo

    lateinit var foo3: Foo

    @UserQualifier(50, 180)
    @Inject
    lateinit var hong: User

    @UserQualifier(10, 150)
    @Inject
    lateinit var gildong: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        assert(::lazyFoo1.isInitialized)
        assert(lazyFoo1.get() != null)
        assert(lazyFoo1.get() !== lazyFoo2.get())
        Log.e(TAG, "Foo :: ${lazyFoo1.get().id}")
        Log.e(TAG, "Foo2 :: ${foo2.id}")
        Log.e(TAG, "Foo3 :: ${foo3.id}")
        Log.e(TAG, "hong :: ${hong.name}")
        Log.e(TAG, "gildong :: ${gildong.name}")

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
    fun injectFoo3(
        @Foo3 foo3: Foo
    ) {
        this.foo3 = foo3
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