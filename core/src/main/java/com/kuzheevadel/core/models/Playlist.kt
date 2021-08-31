package com.kuzheevadel.core.models

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kuzheevadel.core.database.PlaylistInfoConverter
import java.util.*

data class Playlist(
    val id: String,
    val name: String,
    val audioList: List<Audio>
) {
    fun getAudioCount() = audioList.size.toString()

    fun getImageUri(): Uri {
        return if (audioList.isNotEmpty()) {
            audioList[0].imageUri
        } else {
            Uri.EMPTY
        }
    }
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
