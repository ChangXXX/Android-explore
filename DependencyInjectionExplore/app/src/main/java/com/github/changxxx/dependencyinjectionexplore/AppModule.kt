package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNameDiTest(): NameDiTest {
        return NameDiTest()
    }
}