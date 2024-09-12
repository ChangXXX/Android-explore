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
import java.util.Optional
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

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

    @Inject
    lateinit var providerFoo1: Provider<Foo>

    @Inject
    lateinit var providerFoo2: Provider<Foo>

    @UserQualifier(50, 180)
    @Inject
    lateinit var hong: User

    @UserQualifier(10, 150)
    @Inject
    lateinit var gildong: User

    @Inject
    lateinit var car: Car

    @Inject
    lateinit var optionalTest: Optional<OptionalTest>

//    @Inject
//    lateinit var gun: Set<Gun>

    @Inject
    lateinit var weapons: Set<@JvmSuppressWildcards Weapon>

    @Inject
    lateinit var map1:Map<String, Long>

    @Inject
    lateinit var map2:Map<Class<*>, String>

    @Inject
    lateinit var map3:Map<WeaponEnum, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.e(TAG, "knife Value :: ${map1["knife"]}")
        Log.e(TAG, "gun Value :: ${map2[Gun::class.java]}")
        Log.e(TAG, "map value :: ${map3[WeaponEnum.GUN]}")

        weapons.forEach { it ->
            Log.e(TAG, "weapons :: ${it}")
        }

        Log.e(TAG, "엔진 타입 ::: ${car.engine}")

        val providedFoo1 = providerFoo1.get()
        val providedFoo2 = providerFoo1.get()
        assert(providedFoo1 === providedFoo2)

        assert(optionalTest != null)
        Log.e(TAG, "Optional isPresent :: ${optionalTest.isPresent}")

        val optionalTestGet = optionalTest.get()
        Log.e(TAG, "OptionalTest :: $optionalTestGet")

//        assert(::lazyFoo1.isInitialized)
//        assert(lazyFoo1.get() != null)
//        assert(lazyFoo1.get() !== lazyFoo2.get())
        Log.e(TAG, "Foo :: ${lazyFoo1.get()}")
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