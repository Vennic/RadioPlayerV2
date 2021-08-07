package com.kuzheevadel.radioplayerv2.database

import androidx.room.Database
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist

@Database(entities = [Playlist::class, Audio::class], version = 1)
abstract class AppDatabase {
    abstract fun playlistDao(): PlaylistDao
    abstract fun audioDao(): AudioDao
}