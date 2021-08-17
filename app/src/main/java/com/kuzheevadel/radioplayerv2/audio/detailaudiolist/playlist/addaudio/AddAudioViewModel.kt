package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzheevadel.radioplayerv2.usecases.FetchAudioUseCase
import com.kuzheevadel.radioplayerv2.usecases.SaveAudioDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddAudioViewModel @Inject constructor(
    private val fetchAudioUseCase: FetchAudioUseCase,
    private val saveAudioDataUseCase: SaveAudioDataUseCase
): ViewModel() {

    val audioFlow = fetchAudioUseCase.getAllAudioFlow()

    fun addAudioListInPlaylist(audioIdList: List<Long>, playlistPos: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                saveAudioDataUseCase.addAudioIdListInPlaylist(audioIdList, playlistPos)
            }
        }
    }
}