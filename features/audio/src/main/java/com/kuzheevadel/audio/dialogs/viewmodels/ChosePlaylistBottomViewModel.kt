package com.kuzheevadel.audio.dialogs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.audio.common.AudioInfo
import com.kuzheevadel.audio.common.DatabaseState
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.SaveAudioDataUseCase
import com.kuzheevadel.core.common.DestinationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChosePlaylistBottomViewModel @Inject constructor(
    val saveAudioDataUseCase: SaveAudioDataUseCase,
    val fetchAudioUseCase: FetchAudioUseCase
): ViewModel() {

    private val _dbState = MutableStateFlow<DatabaseState>(DatabaseState.None(""))
    val dbState: StateFlow<DatabaseState> = _dbState

    fun addInPlaylistButtonClicked(destType: DestinationType<Nothing>, playlistPos: Int) {
        viewModelScope.launch {
            saveAudioDataUseCase.addAudioInPlaylist(destType, playlistPos)

            val audioName = fetchAudioUseCase.getAudioByDestType(destType).getFullName()
            val playlistName = fetchAudioUseCase.getPlaylistByPosition(playlistPos).name
            _dbState.value = DatabaseState.AudioAdded(audioName, playlistName)
        }
    }

    fun getPlaylists() = fetchAudioUseCase.getAllPlaylistsList()
}