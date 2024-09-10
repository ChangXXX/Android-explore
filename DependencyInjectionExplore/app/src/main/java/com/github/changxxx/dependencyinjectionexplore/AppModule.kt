package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNameDiTest(): NameDiTest {
        return NameDiTest()
    }

    @Provides
    fun provideUnScopedDiTest(): UnScopeDiTest = UnScopeDiTest()

    @Provides
    @Singleton
    fun provideScopedDiTest(): ScopeDiTest = ScopeDiTest()

    @Provides
    fun provideOptionalTest(): OptionalTest {
        return OptionalTest()
    }
}