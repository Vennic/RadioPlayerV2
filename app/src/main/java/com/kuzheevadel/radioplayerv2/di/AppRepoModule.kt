package com.kuzheevadel.radioplayerv2.di

import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepositoryImp
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import dagger.Binds
import dagger.Module

@Module
abstract class AppRepoModule {

    @Binds
    abstract fun providePlayerMediaRepo(repo: PlayerMediaRepositoryImp): PlayerMediaRepository

}