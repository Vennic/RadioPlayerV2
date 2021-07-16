package com.kuzheevadel.radioplayerv2.repositories.datasource

import com.kuzheevadel.radioplayerv2.models.Track

interface TracksDataSourceInterface {
    suspend fun getTracksFromStorage(): List<Track>
}