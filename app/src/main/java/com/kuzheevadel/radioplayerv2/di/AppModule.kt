package com.kuzheevadel.radioplayerv2.di

import android.content.Context
import androidx.room.Room
import com.kuzheevadel.radioplayerv2.database.AppDatabase
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepositoryImp
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    @Binds
    abstract fun providePlayerMediaRepo(repo: PlayerMediaRepositoryImp): PlayerMediaRepository

}