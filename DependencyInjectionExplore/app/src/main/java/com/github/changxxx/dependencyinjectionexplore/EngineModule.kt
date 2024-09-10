package com.github.changxxx.dependencyinjectionexplore

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EngineModule {

//    @Binds
//    abstract fun bindGasolineEngine(engine: GasolineEngine): Engine

    @Binds
    abstract fun bindDieselEngine(engine: DieselEngine): Engine
}