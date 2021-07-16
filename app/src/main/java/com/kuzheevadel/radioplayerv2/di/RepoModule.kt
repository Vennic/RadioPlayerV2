package com.kuzheevadel.radioplayerv2.di

import com.kuzheevadel.radioplayerv2.repositories.TracksRepository
import com.kuzheevadel.radioplayerv2.repositories.TracksRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.TracksDataSource
import com.kuzheevadel.radioplayerv2.repositories.datasource.TracksDataSourceInterface
import com.kuzheevadel.radioplayerv2.tracks.di.TracksFragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {


    @Binds
    abstract fun provideTracksRepo(repo: TracksRepository): TracksRepositoryInterface

    @Binds
    abstract fun provideTracksDataSource(dataSource: TracksDataSource): TracksDataSourceInterface
}