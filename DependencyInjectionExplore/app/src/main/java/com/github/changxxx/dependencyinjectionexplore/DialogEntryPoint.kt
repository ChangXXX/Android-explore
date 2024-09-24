package com.github.changxxx.dependencyinjectionexplore

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(DialogComponent::class)
interface DialogEntryPoint {

    fun getUser(): User
}