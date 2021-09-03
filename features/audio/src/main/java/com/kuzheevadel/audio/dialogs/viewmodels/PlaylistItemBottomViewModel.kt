package com.kuzheevadel.audio.dialogs.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.audio.usecases.EditPlaylistUseCase
import com.kuzheevadel.core.models.Audio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlaylistItemBottomViewModel @Inject constructor(
    private val editPlaylistUseCase: EditPlaylistUseCase
): ViewModel() {

    fun onDeleteAudioClicked(audioPos: Int, playlistPos: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                editPlaylistUseCase.deleteAudioFromPlaylist(audioPos, playlistPos)
            }
        }
    }
}