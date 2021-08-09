package com.kuzheevadel.radioplayerv2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kuzheevadel.radioplayerv2.database.PlaylistInfoConverter
import java.util.*

data class Playlist(
    val id: String,
    val name: String,
    val audioList: List<Audio>
) {
    fun getAudioCount() = audioList.size.toString()
}

@Entity
data class PlaylistInfo(
    @PrimaryKey val id: String,
     val name: String,
    @ColumnInfo(name = "audio_list")
    @TypeConverters(value = [PlaylistInfoConverter::class])
    val audioIdList: List<String>
)

fun generateId() = UUID.randomUUID().toString()
