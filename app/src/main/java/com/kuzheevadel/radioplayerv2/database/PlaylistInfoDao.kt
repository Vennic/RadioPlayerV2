package com.kuzheevadel.radioplayerv2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.models.PlaylistInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistInfoDao {

    @Query("SELECT * FROM playlistinfo ORDER BY name")
    fun getPlaylists(): Flow<List<PlaylistInfo>>

    @Query("SELECT * FROM playlistinfo WHERE name = :playlistName")
    fun getPlaylist(playlistName: String): Flow<PlaylistInfo>

    @Insert
    suspend fun insertPlaylistInfo(playlistInfo: PlaylistInfo)
}