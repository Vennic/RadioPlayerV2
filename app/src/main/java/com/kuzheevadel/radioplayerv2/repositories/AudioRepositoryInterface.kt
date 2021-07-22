package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.Flow

interface AudioRepositoryInterface {
    fun getAudioFlow(): Flow<List<Audio>>
    fun getAllTracks(): List<Audio>
    fun getTrack(position: Int): Audio
    suspend fun loadTracksFromStorage()
}