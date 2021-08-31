package com.kuzheevadel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kuzheevadel.core.models.AudioEntity
import com.kuzheevadel.core.models.PlaylistInfo

@Database(entities = [PlaylistInfo::class, AudioEntity::class], version = 1, exportSchema = false)
@TypeConverters(PlaylistInfoConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun playlistDao(): PlaylistAudioDao
}