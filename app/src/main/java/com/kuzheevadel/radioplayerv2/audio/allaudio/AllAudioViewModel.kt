package com.kuzheevadel.radioplayerv2.audio.allaudio

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import com.kuzheevadel.radioplayerv2.common.MediaType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class AllAudioViewModel @Inject constructor(
        private val audioRepo: AudioRepository,
        private val playerMediaRepo: PlayerMediaRepository
): ViewModel() {

    private val currentMediaFlow = playerMediaRepo.getStateCurrentMediaData()

    @ExperimentalCoroutinesApi
    val audioFlow: Flow<List<Audio>> = currentMediaFlow.flatMapLatest { mediaType ->
        when (mediaType) {
            is MediaType.Audio -> {
                audioRepo.getAudioFlowWithSetState(mediaType.audio)
            }
            else -> {
                audioRepo.getAudioFlow()
            }
        }
    }

    fun onAudioClicked(position: Int) {
        playerMediaRepo.setCurrentAudioMedia(audioRepo.getAllAudio(), position)
    }
}