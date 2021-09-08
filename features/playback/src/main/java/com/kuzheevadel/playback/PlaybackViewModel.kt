package com.kuzheevadel.playback

import androidx.lifecycle.ViewModel
import com.kuzheevadel.core.repositories.PlayerMediaRepository
import javax.inject.Inject

class PlaybackViewModel @Inject constructor(
    private val repository: PlayerMediaRepository
): ViewModel() {

    val currentMediaFLow = repository.getStateCurrentMediaData()

    fun onPlayButtonClicked() {
        repository.playOrPauseMedia()
    }

    fun onNextAudio() {
        repository.setNextAudio()
    }

    fun onPreviousAudio() {
        repository.setPreviousAudio()
    }

}