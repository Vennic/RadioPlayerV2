package com.kuzheevadel.radioplayerv2.database

import androidx.room.*
import com.kuzheevadel.radioplayerv2.models.AudioEntity
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.models.PlaylistInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistAudioDao {

    @Query("SELECT * FROM playlistinfo ORDER BY name")
    fun getPlaylists(): Flow<List<PlaylistInfo>>

    @Query("SELECT * FROM playlistinfo WHERE name = :playlistName")
    fun getPlaylist(playlistName: String): Flow<PlaylistInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaylistInfo(playlistInfo: PlaylistInfo)

    @Update
    suspend fun updatePlaylistInfo(playlistInfo: PlaylistInfo)

    @Query("DELETE FROM playlistinfo WHERE name = :playlistName")
    suspend fun deletePlaylistInfo(playlistName: String)

    @Query("SELECT * FROM audioentity WHERE id = :audioId")
    suspend fun getAudio(audioId: Int): AudioEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAudio(audioList: List<AudioEntity>)

    @Query("DELETE FROM audioentity")
    suspend fun deleteAllAudio()

    @Transaction
    suspend fun deleteAndInsertAudio(audioList: List<AudioEntity>) {
        deleteAllAudio()
        insertAllAudio(audioList)
    }
}