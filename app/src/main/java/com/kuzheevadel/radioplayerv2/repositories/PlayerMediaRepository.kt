package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface PlayerMediaRepository {

    fun getMutableCurrentMediaData(): MutableStateFlow<MediaType<Audio>>
    fun getStateCurrentMediaData(): StateFlow<MediaType<Audio>>
    fun setCurrentAudioMedia(audioList: List<Audio>, position: Int)
    fun setCurrentMediaList()
    fun setNextAudio()
    fun setPreviousAudio()
    fun setRepeatMode(isEnabled: Boolean)
    fun setShuffleMode(isEnabled: Boolean)
}