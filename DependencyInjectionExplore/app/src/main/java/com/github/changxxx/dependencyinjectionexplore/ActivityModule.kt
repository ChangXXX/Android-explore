package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideUnScopedDiTest(): UnScopeDiTest = UnScopeDiTest()

    @Provides
    @ActivityScoped
    fun provideScopedDiTest(): ScopeDiTest = ScopeDiTest()
}