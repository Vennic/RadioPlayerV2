package com.kuzheevadel.radioplayerv2.audio.playlists

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import kotlinx.coroutines.*
import javax.inject.Inject

@AudioFragmentScope
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

    fun onRenamePlaylist(name: String, position: Int) {
        viewModelScope.launch {
            withContext(defDispatcher) {
                audioRepo.renamePlaylist(name, position)
            }
        }
    }

    fun onDeletePlaylist(playlistId: String) {
        viewModelScope.launch {
            withContext(defDispatcher) {
                audioRepo.deletePlaylist(playlistId)
            }
        }
    }
}