package com.kuzheevadel.radioplayerv2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.AudioEntity
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.models.PlaylistInfo

@Database(entities = [PlaylistInfo::class, AudioEntity::class], version = 1, exportSchema = false)
@TypeConverters(PlaylistInfoConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun playlistDao(): PlaylistInfoDao
    abstract fun audioDao(): AudioDao
}