package com.github.changxxx.dependencyinjectionexplore

import android.content.Context
import android.util.Log
import dagger.hilt.EntryPoints

class NameManager {

    fun loggingName(context: Context) {
        val name = EntryPoints.get(context, NameEntryPoint::class.java).getName()

        Log.e("NameManager", "name :: $name")
    }
}