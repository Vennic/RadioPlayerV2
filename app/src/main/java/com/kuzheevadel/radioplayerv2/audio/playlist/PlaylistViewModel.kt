package com.kuzheevadel.radioplayerv2.audio.playlist

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import javax.inject.Inject

class PlaylistViewModel @Inject constructor(
        private val audioRepo: AudioRepository,
        private val playerRepo: PlayerMediaRepository
): ViewModel() {
}