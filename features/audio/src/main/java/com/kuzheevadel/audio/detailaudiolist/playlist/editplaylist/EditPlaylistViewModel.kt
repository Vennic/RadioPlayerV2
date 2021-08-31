package com.kuzheevadel.audio.detailaudiolist.playlist.editplaylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.SaveAudioDataUseCase
import com.kuzheevadel.core.models.Audio
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