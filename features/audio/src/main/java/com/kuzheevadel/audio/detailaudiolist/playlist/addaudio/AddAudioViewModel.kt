package com.kuzheevadel.audio.detailaudiolist.playlist.addaudio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.EditPlaylistUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddAudioViewModel @Inject constructor(
    private val fetchAudioUseCase: FetchAudioUseCase,
    private val editPlaylistUseCase: EditPlaylistUseCase
): ViewModel() {

    val audioFlow = fetchAudioUseCase.getAllAudioFlow()

    fun addAudioListInPlaylist(audioIdList: List<Long>, playlistPos: Int): Boolean {
        var isAdded = false
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                editPlaylistUseCase.addAudioIdListInPlaylist(audioIdList, playlistPos)
            }
            isAdded = true
        }

        return isAdded
    }
}