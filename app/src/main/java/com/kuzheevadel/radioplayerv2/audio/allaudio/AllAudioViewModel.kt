package com.kuzheevadel.radioplayerv2.audio.allaudio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.common.AudioDataType
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import com.kuzheevadel.radioplayerv2.usecases.FetchAudioUseCase
import com.kuzheevadel.radioplayerv2.usecases.SaveAudioDataUseCase
import com.kuzheevadel.radioplayerv2.usecases.SetAudioDataUseCase
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