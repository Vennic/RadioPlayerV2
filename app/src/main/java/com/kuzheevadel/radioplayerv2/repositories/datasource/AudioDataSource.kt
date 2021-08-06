package com.kuzheevadel.radioplayerv2.repositories.datasource

import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.Flow

interface AudioDataSource {
    fun getAudioFlowFromStorage(): Flow<List<Audio>>
}