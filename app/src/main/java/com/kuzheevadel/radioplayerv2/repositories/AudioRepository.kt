package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSourceInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AudioRepository @Inject constructor(
        private val audioDataSource: AudioDataSourceInterface
    ): AudioRepositoryInterface {

    private lateinit var allAudioList: List<Audio>

    override fun getAudioFlow(): Flow<List<Audio>> =
            audioDataSource.getAudioFlowFromStorage()
                    .flowOn(Dispatchers.Default)
                    .conflate()

    override suspend fun loadTracksFromStorage() {

    }

    override fun getTrack(position: Int): Audio = allAudioList[position]

    override fun getAllTracks(): List<Audio> = allAudioList

}