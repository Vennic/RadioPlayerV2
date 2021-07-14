package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.common.QueryResult
import com.kuzheevadel.radioplayerv2.models.Track

interface TracksRepositoryInterface {
    fun getAllTracks(): List<Track>
    suspend fun loadTracksFromStorage()
}