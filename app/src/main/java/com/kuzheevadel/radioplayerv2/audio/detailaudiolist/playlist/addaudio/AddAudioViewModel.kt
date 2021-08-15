package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddAudioViewModel @Inject constructor(
    private val audioRepo: AudioRepository
): ViewModel() {

    val audioFlow = audioRepo.getAudioFlow()

    fun addAudioInPlaylist(audioIdList: List<Long>, playlistPos: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                audioRepo.addAudioListInPlaylist(audioIdList, playlistPos)
                audioRepo.getUpdateListState().value = true
            }
        }
    }
}