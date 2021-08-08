package com.kuzheevadel.radioplayerv2.audio.di

import com.kuzheevadel.radioplayerv2.database.AppDatabase
import com.kuzheevadel.radioplayerv2.database.AudioDao
import com.kuzheevadel.radioplayerv2.database.PlaylistInfoDao
import dagger.Module
import dagger.Provides

@Module
class AudioDatabaseModule {

    @Provides
    fun provideAudioDao(appDatabase: AppDatabase): AudioDao {
        return appDatabase.audioDao()
    }

    @Provides
    fun providePlaylistDao(appDatabase: AppDatabase): PlaylistInfoDao {
        return appDatabase.playlistDao()
    }
}