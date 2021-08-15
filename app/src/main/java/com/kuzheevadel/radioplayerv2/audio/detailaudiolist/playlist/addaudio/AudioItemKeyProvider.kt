package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import androidx.recyclerview.selection.ItemKeyProvider

class AudioItemKeyProvider(private val adapter: AddAudioAdapter)
    : ItemKeyProvider<Long>(SCOPE_CACHED) {
    override fun getKey(position: Int): Long {
        return adapter.currentList[position].id
    }

    override fun getPosition(key: Long): Int {
        return adapter.currentList.indexOfFirst { it.id == key }
    }
}