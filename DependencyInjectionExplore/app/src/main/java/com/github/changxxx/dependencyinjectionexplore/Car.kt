package com.github.changxxx.dependencyinjectionexplore

import javax.inject.Inject

class Car @Inject constructor(
    val engine: Engine
) {
}