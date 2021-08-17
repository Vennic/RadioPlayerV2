package com.kuzheevadel.radioplayerv2.repositories

import com.kuzheevadel.radioplayerv2.models.Album
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.models.PlaylistInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AudioRepository {
    fun getAudioFlow(): Flow<List<Audio>>
    fun getAudioFlowWithSetState(audio: Audio): Flow<List<Audio>>
    fun getAllAudio(): List<Audio>
    fun createAlbumsList(audioList: List<Audio>): List<Album>
    fun getAlbumsStateFlow(): StateFlow<List<Album>>
    fun getAllAlbumsList(): List<Album>
    fun getPlaylistsFlow(): Flow<List<Playlist>>
    fun getAllPlaylists(): List<Playlist>
    suspend fun createPlaylist(name: String)
    suspend fun renamePlaylist(newName: String, position: Int)
    suspend fun deletePlaylist(position: Int)
    suspend fun addAudioInPlaylist(audio: Audio, playlist: Playlist)
    suspend fun addAudioListInPlaylist(audioList: List<Long>, playlistPos: Int)
}