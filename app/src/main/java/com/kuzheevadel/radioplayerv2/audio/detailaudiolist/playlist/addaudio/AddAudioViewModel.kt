package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import javax.inject.Inject

class AddAudioViewModel @Inject constructor(
    private val audioRepo: AudioRepository
): ViewModel() {

    val audioFlow = audioRepo.getAudioFlow()
}