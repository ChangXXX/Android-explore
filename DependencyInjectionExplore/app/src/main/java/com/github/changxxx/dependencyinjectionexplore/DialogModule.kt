package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(DialogComponent::class)
object DialogModule {

    @Provides
    fun provideUser(): User {
        return User("홍길동")
    }
}