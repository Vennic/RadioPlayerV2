package com.kuzheevadel.core.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatchersModule {

    @Provides
    fun provideDefDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}