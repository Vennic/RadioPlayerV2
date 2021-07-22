package com.kuzheevadel.radioplayerv2.repositories.datasource

import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PlayerMediaRepositoryInterface {

    fun getMutableCurrentMediaData(): MutableStateFlow<MediaType<Audio>>
    fun getStateCurrentMediaData(): StateFlow<MediaType<Audio>>
    fun setCurrentTrackMedia(position: Int, audioList: List<Audio>)
    fun setCurrentMediaList()
    fun getNextMedia(): Audio
    fun getPreviousMedia(): Audio
    fun setRepeatMode(isEnabled: Boolean)
    fun setShuffleMode(isEnabled: Boolean)
}