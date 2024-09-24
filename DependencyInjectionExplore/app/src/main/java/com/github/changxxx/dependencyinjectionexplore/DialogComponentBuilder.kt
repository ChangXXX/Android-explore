package com.github.changxxx.dependencyinjectionexplore

import dagger.BindsInstance
import dagger.hilt.DefineComponent
import java.time.LocalDateTime

@DefineComponent.Builder
interface DialogComponentBuilder {

    fun setLocalDate(
        @BindsInstance localDateTime: LocalDateTime,
    ): DialogComponentBuilder

    fun build(): DialogComponent
}