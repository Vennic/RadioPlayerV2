package com.kuzheevadel.radioplayerv2.di

import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.PlayerMediaRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSource
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSourceInterface
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {


    @Binds
    abstract fun provideAudioRepo(repo: AudioRepository): AudioRepositoryInterface

    @Binds
    abstract fun provideAudioDataSource(dataSource: AudioDataSource): AudioDataSourceInterface

    @Binds
    abstract fun provideCurrentMediaRepo(repo: PlayerMediaRepository): PlayerMediaRepositoryInterface
}