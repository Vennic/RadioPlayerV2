package com.kuzheevadel.radioplayerv2.models

data class Playlist(
        val id: Int,
        val name: String,
        val audioList: List<Audio>
) {

    fun getAudioCount() = audioList.size.toString()

    fun getImageUri() = audioList[0].imageUri
}
