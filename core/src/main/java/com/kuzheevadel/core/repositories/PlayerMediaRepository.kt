package com.kuzheevadel.core.repositories

import com.kuzheevadel.core.common.MediaType
import com.kuzheevadel.core.models.Audio
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface PlayerMediaRepository {

    fun getMutableCurrentMediaData(): MutableStateFlow<MediaType<Audio>>
    fun getStateCurrentMediaData(): StateFlow<MediaType<Audio>>
    fun getCurrentAudio(): Audio
    fun setCurrentAudioMedia(audioList: List<Audio>, position: Int)
    fun updateAudioList(audioList: List<Audio>)
    fun setNextAudio()
    fun setPreviousAudio()
    fun setRepeatMode(isEnabled: Boolean)
    fun setShuffleMode(isEnabled: Boolean)

}