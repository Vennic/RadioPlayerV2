package com.kuzheevadel.radioplayerv2.repositories.datasource

import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.Flow

interface AudioDataSourceInterface {
    fun getAudioFlowFromStorage(): Flow<List<Audio>>
}