package com.github.changxxx.dependencyinjectionexplore

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import dagger.hilt.EntryPoints
import javax.inject.Inject

class MyDialog @Inject constructor(
    private val context: Activity,
    private val dialogComponentManager: DialogComponentManager,
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entryPoint = EntryPoints.get(dialogComponentManager, DialogEntryPoint::class.java)
        val user = entryPoint.getUser()

        setContentView(TextView(context).apply {
            text = "Hello ${user.name}"
        })
    }
}