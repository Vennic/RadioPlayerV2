package com.kuzheevadel.radioplayerv2.models

import android.net.Uri

data class Track(
    val uri: Uri,
    val name: String,
    val id: Long,
    val title: String,
    val albumId: Long,
    val imageUri: Uri)
