package com.kuzheevadel.radioplayerv2.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [AppRepoModule::class, AppDatabaseModule::class])
class AppModule {


    @Provides
    fun provideDefDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}