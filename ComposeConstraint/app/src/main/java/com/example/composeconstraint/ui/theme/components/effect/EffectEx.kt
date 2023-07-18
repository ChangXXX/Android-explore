package com.example.composeconstraint.ui.theme.components.effect

import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun EffectEx(
    scaffoldState: ScaffoldState,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    LaunchedEffect(scaffoldState.snackbarHostState) {
        scaffoldState.snackbarHostState.showSnackbar("뉴클리어 런치드 디텍트")
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    Log.d("LIFECYCLE", "ON_START")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("LIFECYCLE", "ON_RESUME")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("LIFECYCLE", "ON_STOP")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("LIFECYCLE", "ON_PAUSE")
                }
                else -> {
                    Log.d("LIFECYCLE", "else")
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
