package com.kuzheevadel.audio.usecases

import com.kuzheevadel.core.common.DestinationType
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.core.models.PlaylistInfo
import com.kuzheevadel.core.repositories.AudioRepository
import javax.inject.Inject

class SaveAudioDataUseCaseImpl @Inject constructor(
    private val audioRepo: AudioRepository
): SaveAudioDataUseCase {

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
}

interface SaveAudioDataUseCase {
    suspend fun addAudioInPlaylist(destType: DestinationType<Nothing>, playlistPos: Int)
    suspend fun addAudioIdListInPlaylist(audioIdList: List<Long>, playlistPos: Int)
    suspend fun addAudioListInPlaylist(audioList: List<Audio>, playlistPos: Int)
}