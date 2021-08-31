package com.kuzheevadel.audio.allaudio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.SaveAudioDataUseCase
import com.kuzheevadel.audio.usecases.SetAudioDataUseCase
import com.kuzheevadel.core.common.AudioDataType
import com.kuzheevadel.core.models.Audio
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllAudioViewModel @Inject constructor(
    private val fetchAudioDataUseCase: FetchAudioUseCase,
    private val setAudioDataUseCase: SetAudioDataUseCase,
    private val saveAudioDataUseCase: SaveAudioDataUseCase,
): ViewModel() {

    val audioFlow = fetchAudioDataUseCase.getAllAudioFlow()

    fun onAudioClicked(position: Int) {
        setAudioDataUseCase.setCurrentAudioData(AudioDataType.ALL_AUDIO, audioPosition = position)
    }

    fun getAudioByPos(position: Int): Audio {
        return fetchAudioDataUseCase.getAudioByPosFromAllAudio(position)
    }

    fun addInPlaylistButtonClicked(audioPosition: Int, playlistPos: Int) {
        viewModelScope.launch {
            saveAudioDataUseCase.addAudioInPlaylist(audioPosition, playlistPos)
        }
    }

    fun getPlaylists() = fetchAudioDataUseCase.getAllPlaylistsList()
}