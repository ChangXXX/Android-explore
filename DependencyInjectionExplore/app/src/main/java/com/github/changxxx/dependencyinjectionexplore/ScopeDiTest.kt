package com.github.changxxx.dependencyinjectionexplore

import dagger.hilt.android.scopes.ActivityScoped
import java.util.UUID
import javax.inject.Inject

class ScopeDiTest{

    private val id = UUID.randomUUID()

    override fun toString(): String {
        return "ScopedDiTest toString $id"
    }
}