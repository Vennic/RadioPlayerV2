package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.models.Track
import com.kuzheevadel.radioplayerv2.repositories.datasource.CurrentMediaRepositoryInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentMediaRepository @Inject constructor(): CurrentMediaRepositoryInterface {

    override fun setCurrentMedia() {
        TODO("Not yet implemented")
    }

    override fun setCurrentMediaList() {
        TODO("Not yet implemented")
    }

    override fun getNextMedia(): Track {
        TODO("Not yet implemented")
    }

    override fun getPreviousMedia(): Track {
        TODO("Not yet implemented")
    }

    override fun setRepeatMode(isEnabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setShuffleMode(isEnabled: Boolean) {
        TODO("Not yet implemented")
    }
}