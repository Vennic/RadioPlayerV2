package com.kuzheevadel.radioplayerv2.audio.detailalbum

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.PlayerMediaRepositoryInterface
import com.kuzheevadel.radioplayerv2.utils.setAllAudioUnselected
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailedAlbumViewModel @Inject constructor(
    private val audioRepo: AudioRepositoryInterface,
    private val playerRepo: PlayerMediaRepositoryInterface

): ViewModel() {

    init {
        Log.d("TYUI", "detailed album - $audioRepo")
    }

    lateinit var audioList: List<Audio>
    private val currentMediaFlow = playerRepo.getStateCurrentMediaData()

    @ExperimentalCoroutinesApi
    val audioFLow: Flow<List<Audio>> = currentMediaFlow.flatMapLatest {
        if (it is MediaType.Audio) {
            flow { emit(audioList.setAudioState(it.audio)) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
        } else {
            flow { emit(audioList.setAllAudioUnselected()) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
        }
    }

    fun prepareFlow(position: Int) {
        Log.d("TYUI", "prepareFlow - ${audioRepo.getAlbumAudioList(position)}")
        audioList = audioRepo.getAlbumAudioList(position)
    }
}