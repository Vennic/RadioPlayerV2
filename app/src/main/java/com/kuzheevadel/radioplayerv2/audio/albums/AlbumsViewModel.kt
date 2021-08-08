package com.kuzheevadel.radioplayerv2.audio.albums

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
        private val audioRepo: AudioRepository
): ViewModel() {

    init {
        Log.d("TYUI", "albums - $audioRepo")
    }

    val albumsData = audioRepo.getAlbumsStateFlow()
}