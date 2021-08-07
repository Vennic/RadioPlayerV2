package com.kuzheevadel.radioplayerv2.models

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Audio(
    @Ignore val uri: Uri,
    val name: String,
    @PrimaryKey val id: Long,
    val title: String,
    @ColumnInfo(name = "artist_name") val artistName: String,
    @ColumnInfo(name = "album_title") val albumTitle: String,
    val duration: Int,
    @ColumnInfo(name = "album_id") val albumId: Long,
    @Ignore val imageUri: Uri,
    @Ignore var isSelected: Boolean,
    @Ignore var isPlaying: Boolean) {

    fun getNameAndDuration(): String {
        return "$artistName • ${getDurationInTimeFormat()}"
    }

    fun getFullName() = "$artistName - $title"

    fun getDurationInTimeFormat(): String {
        val d = duration / 1000
        val minutes = d / 60
        val seconds = d % 60
        return "$minutes:${if (seconds < 10) "0" else ""}$seconds"
    }
}
