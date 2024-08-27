package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @UserQualifier(50, 180)
    fun provideHong(): User{
        return User("Hong")
    }

    @Provides
    @UserQualifier(10, 150)
    fun provideGilDong(): User{
        return User("GilDong")
    }
}