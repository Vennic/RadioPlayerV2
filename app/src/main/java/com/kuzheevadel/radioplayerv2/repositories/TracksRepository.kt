package com.kuzheevadel.radioplayerv2.repositories

import android.util.Log
import com.kuzheevadel.radioplayerv2.common.QueryResult
import com.kuzheevadel.radioplayerv2.models.Track
import com.kuzheevadel.radioplayerv2.repositories.datasource.MediaDataSourceInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class TracksRepository @Inject constructor(
    private val mediaDataSource: MediaDataSourceInterface,
    ): TracksRepositoryInterface {

    private lateinit var allTracksList: List<Track>

    override suspend fun loadTracksFromStorage() {
        withContext(Dispatchers.IO) {
            Thread.sleep(3000)
                allTracksList = mediaDataSource.getTracksFromStorage()
        }
    }

    override fun getAllTracks(): List<Track> = allTracksList

}