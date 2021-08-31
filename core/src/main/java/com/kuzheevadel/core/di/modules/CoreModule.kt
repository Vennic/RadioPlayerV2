package com.kuzheevadel.core.di.modules

import android.app.Application
import android.content.Context
import com.kuzheevadel.core.repositories.datasource.AudioDataSource
import com.kuzheevadel.core.repositories.datasource.AudioDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule(val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = application
}