package com.kuzheevadel.radioplayerv2.audio.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@AudioFragmentScope
class PlaylistViewModel @Inject constructor(
    private val audioRepo: AudioRepository,
    private val defDispatcher: CoroutineDispatcher
    ): ViewModel() {


    val playlistData = audioRepo.getPlaylistsFlow()

    private var _renamePlaylistData = MutableStateFlow<Int?>(null)
    val renamePlaylistData: StateFlow<Int?> = _renamePlaylistData

    fun onCreateNewPlaylist(name: String) {
        viewModelScope.launch {
            withContext(defDispatcher) {
                audioRepo.createPlaylist(name)
            }
        }
    }

    fun setPlaylistData(pos: Int?) {
        _renamePlaylistData.value = pos
    }

    fun onRenamePlaylist(name: String, position: Int) {
        viewModelScope.launch {
            withContext(defDispatcher) {
                audioRepo.renamePlaylist(name, position)
            }
        }
    }

    fun onDeletePlaylist(playlistPos: Int) {
        viewModelScope.launch {
            withContext(defDispatcher) {
                audioRepo.deletePlaylist(playlistPos)
            }
        }
    }
}