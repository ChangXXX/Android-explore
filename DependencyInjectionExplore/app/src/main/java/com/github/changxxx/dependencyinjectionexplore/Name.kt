package com.github.changxxx.dependencyinjectionexplore

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class Name @Inject constructor() {
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NameEntryPoint {

    fun getName(): Name
}