package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
class WeaponModule {

//    @Provides
//    @IntoSet
//    fun provideGun() = Gun()
//
//    @Provides
//    @IntoSet
//    fun provideKnife() = Knife()

    @Provides
    @ElementsIntoSet
    fun provideWeapons() = setOf(Gun(), Knife())
}