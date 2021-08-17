package com.kuzheevadel.radioplayerv2.audio.albums

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.usecases.FetchAudioUseCase
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
        private val fetchAudioUseCase: FetchAudioUseCase
): ViewModel() {

    val albumsData = fetchAudioUseCase.getAlbumsFlow()
}