package com.kuzheevadel.radioplayerv2.audio.di

import com.kuzheevadel.radioplayerv2.database.AppDatabase
import com.kuzheevadel.radioplayerv2.database.PlaylistAudioDao
import dagger.Module
import dagger.Provides

@Module
class AudioDatabaseModule {

    @Provides
    fun providePlaylistDao(appDatabase: AppDatabase): PlaylistAudioDao {
        return appDatabase.playlistDao()
    }
}