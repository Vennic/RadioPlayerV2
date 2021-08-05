package com.kuzheevadel.radioplayerv2.audio.detailalbum

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import javax.inject.Inject

class DetailedAlbumViewModel @Inject constructor(
    private val audioRepo: AudioRepositoryInterface
): ViewModel() {

}