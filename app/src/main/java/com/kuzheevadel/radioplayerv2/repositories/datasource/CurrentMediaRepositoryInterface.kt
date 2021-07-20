package com.kuzheevadel.radioplayerv2.repositories.datasource

import com.kuzheevadel.radioplayerv2.models.Track

interface CurrentMediaRepositoryInterface {
    fun setCurrentMedia()
    fun setCurrentMediaList()
    fun getNextMedia(): Track
    fun getPreviousMedia(): Track
    fun setRepeatMode(isEnabled: Boolean)
    fun setShuffleMode(isEnabled: Boolean)
}