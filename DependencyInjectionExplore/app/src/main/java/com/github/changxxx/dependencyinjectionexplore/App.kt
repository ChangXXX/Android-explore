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

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "Di Logging $nameDiTest")
    }
}