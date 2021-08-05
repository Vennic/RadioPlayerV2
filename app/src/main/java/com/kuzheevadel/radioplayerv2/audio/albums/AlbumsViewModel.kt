package com.kuzheevadel.radioplayerv2.audio.albums

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
        private val audioRepo: AudioRepositoryInterface
): ViewModel() {

    init {
        Log.d("TYUI", "albums - $audioRepo")
    }

    var albumsData = audioRepo.getAlbumsStateFlow()
}