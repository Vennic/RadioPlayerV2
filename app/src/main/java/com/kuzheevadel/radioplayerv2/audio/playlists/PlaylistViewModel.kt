package com.kuzheevadel.radioplayerv2.audio.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaylistViewModel @Inject constructor(
    private val audioRepo: AudioRepository,
    private val defDispatcher: CoroutineDispatcher
    ): ViewModel() {


    val playlistData = audioRepo.getPlaylistsFlow()

    fun onCreateNewPlaylist(name: String) {
        viewModelScope.launch {
            withContext(defDispatcher) {
                audioRepo.createPlaylist(name)
            }
        }
    }

    fun onDeletePlaylist(name: String) {
        viewModelScope.launch {
            withContext(defDispatcher) {
                audioRepo.deletePlaylist(name)
            }
        }
    }
}