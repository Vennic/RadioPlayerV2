package com.kuzheevadel.audio.albums

import androidx.lifecycle.ViewModel
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
        fetchAudioUseCase: FetchAudioUseCase
): ViewModel() {

    val albumsData = fetchAudioUseCase.getAlbumsFlow()
}