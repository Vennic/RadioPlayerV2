package com.kuzheevadel.radioplayerv2.audio.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaylistViewModel @Inject constructor(
        private val audioRepo: AudioRepository,
        private val playerRepo: PlayerMediaRepository
): ViewModel() {

        val playlistData = audioRepo.getPlaylists()

        fun createNewPlaylist(name: String) {
                viewModelScope.launch {
                        withContext(Dispatchers.Default) {
                                audioRepo.createPlaylist(name)
                        }
                }
        }
}