package com.kuzheevadel.radioplayerv2.playback

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.repositories.datasource.CurrentMediaRepositoryInterface
import javax.inject.Inject
import javax.inject.Singleton


class PlaybackViewModel @Inject constructor(
        private val repository: CurrentMediaRepositoryInterface): ViewModel()
{

    fun getRepo(): String {
        return repository.toString()
    }

}