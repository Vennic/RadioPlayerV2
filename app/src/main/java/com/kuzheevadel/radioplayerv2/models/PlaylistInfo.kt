package com.kuzheevadel.radioplayerv2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kuzheevadel.radioplayerv2.database.PlaylistInfoConverter

@Entity
data class PlaylistInfo(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "audio_list")
    @TypeConverters(value = [PlaylistInfoConverter::class])
    val audioIdList: List<String>
)
