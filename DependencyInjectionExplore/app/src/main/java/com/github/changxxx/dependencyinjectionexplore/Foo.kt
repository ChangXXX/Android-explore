package com.github.changxxx.dependencyinjectionexplore

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Foo @Inject constructor(val bar: Bar, val id: String) {
}