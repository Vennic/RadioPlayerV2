package com.kuzheevadel.radioplayerv2.playback

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.datasource.PlayerMediaRepositoryInterface
import javax.inject.Inject


class PlaybackViewModel @Inject constructor(
        private val repository: PlayerMediaRepositoryInterface): ViewModel()
{

    fun getRepo(): String {
        return repository.toString()
    }

}