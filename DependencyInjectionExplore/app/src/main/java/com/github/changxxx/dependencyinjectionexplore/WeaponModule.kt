package com.github.changxxx.dependencyinjectionexplore

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.StringKey

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

    @Provides
    @IntoMap @StringKey("knife")
    fun provideKnifeValue(): Long {
        return 100L
    }

    @Provides
    @IntoMap @ClassKey(Gun::class)
    fun provideGunValue(): String {
        return "value for Gun"
    }

    @Provides
    @IntoMap @WeaponKey(WeaponEnum.GUN)
    fun provideGunString(): String {
        return "총을 슈루룩"
    }

    @Provides
    @IntoMap @WeaponKey(WeaponEnum.KNIFE)
    fun provideKnifeString(): String {
        return "칼을 슈루룩"
    }
}