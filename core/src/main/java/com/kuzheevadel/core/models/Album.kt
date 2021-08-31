package com.kuzheevadel.core.models

import android.net.Uri

data class Album(
        val name: String,
        val audioList: List<Audio>
) {
    fun getImageUri(): Uri {
        return audioList[0].imageUri
    }
}
