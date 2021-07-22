package com.kuzheevadel.radioplayerv2.audio

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.common.QueryResult
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.PlayerMediaRepositoryInterface
import com.kuzheevadel.radioplayerv2.audio.di.TracksFragmentScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@TracksFragmentScope
class AudioViewModel @Inject constructor(
        private val audioRepo: AudioRepositoryInterface,
        private val playerMediaRepo: PlayerMediaRepositoryInterface
): ViewModel() {

    private val currentMediaFlow = playerMediaRepo.getMutableCurrentMediaData()

    val audioFlow: Flow<List<Audio>> = audioRepo.getAudioFlow()

    init {

    }

    private val _loadState =
            MutableStateFlow<QueryResult<List<Audio>>>(QueryResult.Loading(false))
    val loadState: StateFlow<QueryResult<List<Audio>>> = _loadState

    fun onPermissionGranted() {
        viewModelScope.launch {
            _loadState.value = QueryResult.Loading(true)

            try {
                audioRepo.loadTracksFromStorage()
            } catch (error: Throwable) {
                _loadState.value = QueryResult.Error(error)
                _loadState.value = QueryResult.Loading(false)
            }

            _loadState.value = QueryResult.Loading(false)
            _loadState.value = QueryResult.Success(audioRepo.getAllTracks())
        }
    }

    fun onTrackClicked(position: Int) {
        playerMediaRepo.setCurrentTrackMedia(position, audioRepo.getAllTracks())
        Log.d("VBNM", "onTrackCLicked $position")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ASDC", "TracksViewModel onCleared")
    }

}