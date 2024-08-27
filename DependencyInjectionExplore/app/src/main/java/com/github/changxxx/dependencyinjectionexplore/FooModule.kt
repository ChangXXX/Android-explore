package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object FooModule {

    @Provides
    fun provideFoo1(bar: Bar): Foo {
        return Foo(bar, "Foo1")
    }

    @Named("Foo2")
    @Provides
    fun provideFoo2(bar: Bar): Foo {
        return Foo(bar, "Foo2")
    }

    @Foo3
    @Provides
    fun provideFoo3(bar: Bar): Foo {
        return Foo(bar, "Foo3")
    }
}

@Qualifier
annotation class Foo3