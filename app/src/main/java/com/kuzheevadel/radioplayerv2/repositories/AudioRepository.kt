package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSourceInterface
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AudioRepository @Inject constructor(
        private val audioDataSource: AudioDataSourceInterface
    ): AudioRepositoryInterface {

    var allAudioList = listOf<Audio>()

    //Dispatcher provide!!
    override fun getAudioFlow(): Flow<List<Audio>> =
            audioDataSource.getAudioFlowFromStorage()
                    .onEach {
                        allAudioList = it
                    }
                    .flowOn(Dispatchers.Default)
                    .conflate()

    //Dispatcher provide!!
    override fun getAudioFlowWithSetState(audio: Audio): Flow<List<Audio>> {
        return if (allAudioList.isNullOrEmpty()) {
            audioDataSource.getAudioFlowFromStorage()
                    .map { audioList ->
                        audioList.setAudioState(audio)
                    }
                    .flowOn(Dispatchers.Default)
                    .conflate()
        } else {
            val newList = allAudioList.map { it.copy() }

            flow { emit(newList)}
                    .map { audioList ->
                        audioList.setAudioState(audio)
                    }
                    .flowOn(Dispatchers.Default)
                    .conflate()

        }
    }

    override suspend fun loadTracksFromStorage() {

    }

    override fun getTrack(position: Int): Audio = allAudioList[position]

    override fun getAllTracks(): List<Audio> = allAudioList

}