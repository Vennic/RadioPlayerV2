package com.kuzheevadel.radioplayerv2.database

import androidx.room.Dao
import androidx.room.Query
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.models.PlaylistInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Query("SELECT * FROM playlistinfo ORDER BY name")
    fun getPlaylists(): Flow<List<PlaylistInfo>>

    @Query("SELECT * FROM playlistinfo WHERE id = :playlistId")
    fun getPlaylist(playlistId: Int): Flow<Playlist>
}