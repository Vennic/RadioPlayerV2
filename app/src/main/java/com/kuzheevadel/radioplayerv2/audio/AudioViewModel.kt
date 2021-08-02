package com.kuzheevadel.radioplayerv2.audio

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.PlayerMediaRepositoryInterface
import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.common.MediaType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@AudioFragmentScope
class AudioViewModel @Inject constructor(
        private val audioRepo: AudioRepositoryInterface,
        private val playerMediaRepo: PlayerMediaRepositoryInterface
): ViewModel() {

    init {
        Log.d("VBNM", "$this")
    }

    private val currentMediaFlow = playerMediaRepo.getStateCurrentMediaData()

    @ExperimentalCoroutinesApi
    val audioFlow: Flow<List<Audio>> = currentMediaFlow.flatMapLatest { mediaType ->
        when (mediaType) {
            is MediaType.Track -> {
                audioRepo.getAudioFlowWithSetState(mediaType.track)
            }
            else -> {
                audioRepo.getAudioFlow()
            }
        }
    }

    fun onTrackClicked(position: Int) {
        playerMediaRepo.setCurrentAudioMedia(audioRepo.getAllTracks(), position)
    }
}