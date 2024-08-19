package com.github.changxxx.dependencyinjectionexplore

import java.util.UUID


class UnScopeDiTest {

    private val id = UUID.randomUUID()

    override fun toString(): String {
        return "UnScopedDiTest toString $id"
    }
}