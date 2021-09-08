package com.kuzheevadel.core.datasource

import com.kuzheevadel.core.models.Audio
import kotlinx.coroutines.flow.Flow

interface AudioDataSource {
    fun getAudioFlowFromStorage(): Flow<List<Audio>>
}