package com.kuzheevadel.core.database

import androidx.room.*
import com.kuzheevadel.core.models.AudioEntity
import com.kuzheevadel.core.models.Playlist
import com.kuzheevadel.core.models.PlaylistInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistAudioDao {

    @Query("SELECT * FROM playlistinfo")
    fun getPlaylists(): Flow<List<PlaylistInfo>>

    @Query("SELECT * FROM playlistinfo WHERE name = :id")
    fun getPlaylist(id: String): Flow<PlaylistInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaylistInfo(playlistInfo: PlaylistInfo)

    @Update
    suspend fun updatePlaylistInfo(playlistInfo: PlaylistInfo)

    @Query("DELETE FROM playlistinfo WHERE id = :playlistId")
    suspend fun deletePlaylistInfo(playlistId: String)

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