package com.kuzheevadel.radioplayerv2.audio.albums

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
        private val audioRepo: AudioRepositoryInterface
): ViewModel() {

    var albumsData = audioRepo.getAlbumsStateFlow()
}