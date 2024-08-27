package com.github.changxxx.dependencyinjectionexplore

import javax.inject.Inject

class Foo @Inject constructor(val bar: Bar, val id: String) {
}