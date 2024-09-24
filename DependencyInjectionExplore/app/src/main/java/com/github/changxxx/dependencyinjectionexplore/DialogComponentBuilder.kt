package com.github.changxxx.dependencyinjectionexplore

import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface DialogComponentBuilder {

    fun build(): DialogComponent
}