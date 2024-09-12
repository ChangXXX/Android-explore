package com.github.changxxx.dependencyinjectionexplore

import dagger.MapKey

interface Weapon

class Gun : Weapon

class Knife: Weapon

enum class WeaponEnum {
    GUN,
    KNIFE
}

@MapKey
annotation class WeaponKey(val value: WeaponEnum)