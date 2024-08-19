package com.github.changxxx.dependencyinjectionexplore

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    val TAG = App::class.java.simpleName

    @Inject
    lateinit var nameDiTest: NameDiTest

    @Inject
    lateinit var scopeDiTest: ScopeDiTest

    @Inject
    lateinit var unScopeDiTest: UnScopeDiTest

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "Di Logging $nameDiTest")
        Log.e(TAG, "app scoped Logging $scopeDiTest")
        Log.e(TAG, "app unscoped Logging $unScopeDiTest")
    }
}