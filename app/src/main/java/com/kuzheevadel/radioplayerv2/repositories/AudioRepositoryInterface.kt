package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.models.Album
import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AudioRepositoryInterface {
    fun getAudioFlow(): Flow<List<Audio>>
    fun getAudioFlowWithSetState(audio: Audio): Flow<List<Audio>>
    fun getAllAudio(): List<Audio>
    fun createAlbumsList(audioList: List<Audio>): List<Album>
    fun getAlbumsStateFlow(): StateFlow<List<Album>>
    fun getAlbumAudioList(position: Int): List<Audio>
    fun getAlbum(position: Int): Album
}