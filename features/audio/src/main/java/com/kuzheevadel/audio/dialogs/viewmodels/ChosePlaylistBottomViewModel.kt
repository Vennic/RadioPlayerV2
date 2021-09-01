package com.kuzheevadel.audio.dialogs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.SaveAudioDataUseCase
import com.kuzheevadel.core.common.DestinationType
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChosePlaylistBottomViewModel @Inject constructor(
    val saveAudioDataUseCase: SaveAudioDataUseCase,
    val fetchAudioUseCase: FetchAudioUseCase
): ViewModel() {

    fun addInPlaylistButtonClicked(destType: DestinationType<Nothing>, playlistPos: Int) {
        viewModelScope.launch {
            saveAudioDataUseCase.addAudioInPlaylist(destType, playlistPos)
        }
    }

    fun getPlaylists() = fetchAudioUseCase.getAllPlaylistsList()
}