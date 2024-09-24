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
        val num1 = entryPoint.getRandomNumber()
        val num2 = entryPoint.getRandomNumber()
        val num3 = entryPoint.getRandomNumber()
        val localDateTime = entryPoint.getLocalDateTime()
        println("localDateTime :: $localDateTime")
        setContentView(TextView(context).apply {
            text = buildString {
                append("Hello ")
                append(user.name)
                append(" \n ")
                append(num1)
                append(" \n ")
                append(num2)
                append(" \n ")
                append(num3)
                append(" \n ")
                append(localDateTime.toString())
            }
        })
    }
}