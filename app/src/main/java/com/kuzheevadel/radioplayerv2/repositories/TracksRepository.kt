package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.models.Track
import com.kuzheevadel.radioplayerv2.repositories.datasource.TracksDataSourceInterface
import com.kuzheevadel.radioplayerv2.tracks.di.TracksFragmentScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TracksRepository @Inject constructor(
        private val tracksDataSource: TracksDataSourceInterface,
    ): TracksRepositoryInterface {

    private lateinit var allTracksList: List<Track>

    override suspend fun loadTracksFromStorage() {
        withContext(Dispatchers.IO) {
            Thread.sleep(3000)
                allTracksList = tracksDataSource.getTracksFromStorage()
        }
    }

    override fun getAllTracks(): List<Track> = allTracksList

}