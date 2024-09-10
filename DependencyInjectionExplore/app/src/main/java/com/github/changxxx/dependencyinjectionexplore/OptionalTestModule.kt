package com.github.changxxx.dependencyinjectionexplore

import dagger.BindsOptionalOf
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OptionalTestModule {

    @BindsOptionalOf
    abstract fun optionalOptionalTestModule(): OptionalTest
}