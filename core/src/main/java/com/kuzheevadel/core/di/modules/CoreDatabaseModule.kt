package com.kuzheevadel.core.di.modules

import android.content.Context
import androidx.room.Room
import com.kuzheevadel.core.database.AppDatabase
import com.kuzheevadel.core.database.PlaylistAudioDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreDatabaseModule {

    @Singleton
    @Provides
    fun providePlaylistDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "PlaylistD"
        ).build()

    @Singleton
    @Provides
    fun providePlaylistDao(appDatabase: AppDatabase): PlaylistAudioDao {
        return appDatabase.playlistDao()
    }
}