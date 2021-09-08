package com.kuzheevadel.core.di.modules

import com.kuzheevadel.core.repositories.AudioRepository
import com.kuzheevadel.core.repositories.AudioRepositoryImp
import com.kuzheevadel.core.repositories.PlayerMediaRepository
import com.kuzheevadel.core.repositories.PlayerMediaRepositoryImp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CoreRepoModule {

    @Singleton
    @Binds
    abstract fun provideAudioRepo(repo: AudioRepositoryImp): AudioRepository

    @Singleton
    @Binds
    abstract fun providePlayerMediaRepo(repo: PlayerMediaRepositoryImp): PlayerMediaRepository

}