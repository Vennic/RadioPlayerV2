package com.kuzheevadel.core.repositories

import com.kuzheevadel.core.models.Album
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.core.models.Playlist
import com.kuzheevadel.core.models.PlaylistInfo
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
    suspend fun addAudioListInPlaylist(playlistInfo: PlaylistInfo)
}