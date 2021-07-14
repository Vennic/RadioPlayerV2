package com.kuzheevadel.radioplayerv2.di

import com.kuzheevadel.radioplayerv2.repositories.TracksRepository
import com.kuzheevadel.radioplayerv2.repositories.TracksRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.MediaDataSource
import com.kuzheevadel.radioplayerv2.repositories.datasource.MediaDataSourceInterface
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    abstract fun provideTracksRepo(repo: TracksRepository): TracksRepositoryInterface

    @Binds
    abstract fun provideMediaDataSource(dataSource: MediaDataSource): MediaDataSourceInterface
}