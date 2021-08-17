package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.editplaylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.usecases.FetchAudioUseCase
import com.kuzheevadel.radioplayerv2.usecases.SaveAudioDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditPlaylistViewModel @Inject constructor(
    private val fetchAudioUseCase: FetchAudioUseCase,
    private val saveAudioDataUseCase: SaveAudioDataUseCase
): ViewModel() {

    fun editPlaylistComplete(audioList: List<Audio>, playlistPos: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                saveAudioDataUseCase.addAudioListInPlaylist(audioList, playlistPos)
            }
        }
    }

    fun getAudioList(playlistPos: Int): List<Audio> {
        return fetchAudioUseCase.getPlaylistByPosition(playlistPos).audioList
    }
}