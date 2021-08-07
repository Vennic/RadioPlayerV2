package com.kuzheevadel.radioplayerv2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kuzheevadel.radioplayerv2.database.PlaylistConverter

data class Playlist(
        val id: Int,
        val name: String,
        val audioList: List<Audio>
) {

    fun getAudioCount() = audioList.size.toString()

    fun getImageUri() = audioList[0].imageUri
}

@Entity
data class PlaylistInfo(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "audio_list")
    @TypeConverters(value = [PlaylistConverter::class])
    val audioIdList: List<String>
)
