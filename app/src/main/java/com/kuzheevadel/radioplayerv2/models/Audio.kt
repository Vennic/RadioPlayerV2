package com.kuzheevadel.radioplayerv2.models

import android.net.Uri

data class Audio(
    val uri: Uri,
    val name: String,
    val id: Long,
    val title: String,
    val artistName: String,
    val albumTitle: String,
    val duration: Int,
    val albumId: Long,
    val imageUri: Uri,
    var isSelected: Boolean,
    var isPlaying: Boolean) {

    fun getNameAndDuration(): String {
        return "$artistName • ${getDurationInTimeFormat()}"
    }

    fun getDurationInTimeFormat(): String {
        val d = duration / 1000
        val minutes = d / 60
        val seconds = d % 60
        return "$minutes:${if (seconds < 10) "0" else ""}$seconds"
    }
}
