package com.kuzheevadel.audio.usecases

import com.kuzheevadel.core.common.DestinationType
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.core.models.PlaylistInfo
import com.kuzheevadel.core.repositories.AudioRepository
import javax.inject.Inject

class EditPlaylistUseCaseImpl @Inject constructor(
    private val audioRepo: AudioRepository
): EditPlaylistUseCase {

    override suspend fun addAudioInPlaylist(
        destType: DestinationType<Nothing>,
        playlistPos: Int
    ) {
        when(destType) {
            is DestinationType.AllAudio -> audioRepo.addAudioInPlaylist(
                audioRepo.getAllAudio()[destType.audioPos], audioRepo.getAllPlaylists()[playlistPos]
            )

            is DestinationType.Album -> {
                val audio = audioRepo.getAllAlbumsList()[destType.albumPos]
                    .audioList[destType.audioPos]
                audioRepo.addAudioInPlaylist(audio, audioRepo.getAllPlaylists()[playlistPos])
            }

            is DestinationType.Playlist -> {}
        }
    }

    override suspend fun addAudioIdListInPlaylist(audioIdList: List<Long>, playlistPos: Int) {
        val idList = audioIdList.map { it.toString() }
        val playlist = audioRepo.getAllPlaylists()[playlistPos]
        val currentIdList = playlist.audioList.map { it.id.toString() }.toMutableList()

        for (item in idList) {
            if (!currentIdList.contains(item))
                currentIdList.add(item)
        }

        val playlistInfo = PlaylistInfo(playlist.id, playlist.name, currentIdList)
        audioRepo.addAudioListInPlaylist(playlistInfo)
    }

    override suspend fun addAudioListInPlaylist(audioList: List<Audio>, playlistPos: Int) {
        val idList = audioList.map { it.id.toString() }
        val playlist = audioRepo.getAllPlaylists()[playlistPos]

        val playlistInfo = PlaylistInfo(playlist.id, playlist.name, idList)
        audioRepo.addAudioListInPlaylist(playlistInfo)
    }

    override suspend fun deleteAudioFromPlaylist(audioPos: Int, playlistPos: Int) {
        val audioList = audioRepo.getAllPlaylists()[playlistPos].audioList.toMutableList()
        val audio = audioList[audioPos]

        if (audioList.remove(audio)) {
            val idList = audioList.map { it.id.toString() }
            val playlist = audioRepo.getAllPlaylists()[playlistPos]
            val playlistInfo = PlaylistInfo(playlist.id, playlist.name, idList)
            audioRepo.addAudioListInPlaylist(playlistInfo)
        }
    }
}

interface EditPlaylistUseCase {
    suspend fun addAudioInPlaylist(destType: DestinationType<Nothing>, playlistPos: Int)
    suspend fun addAudioIdListInPlaylist(audioIdList: List<Long>, playlistPos: Int)
    suspend fun addAudioListInPlaylist(audioList: List<Audio>, playlistPos: Int)
    suspend fun deleteAudioFromPlaylist(audioPos: Int, playlistPos: Int)
}