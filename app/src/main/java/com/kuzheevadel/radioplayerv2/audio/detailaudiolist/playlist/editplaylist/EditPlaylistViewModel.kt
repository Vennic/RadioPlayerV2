package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.editplaylist

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.usecases.FetchAudioUseCase
import javax.inject.Inject

class EditPlaylistViewModel @Inject constructor(
    private val fetchAudioUseCase: FetchAudioUseCase,
): ViewModel() {

    val audioFlow = fetchAudioUseCase.getAllAudioFlow()

    fun addAudioInPlaylist() {

    }

    fun getAudioList(playlistPos: Int): List<Audio> {
        return fetchAudioUseCase.getPlaylistByPosition(playlistPos).audioList
    }
}