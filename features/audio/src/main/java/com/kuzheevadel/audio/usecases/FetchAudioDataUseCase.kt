package com.kuzheevadel.audio.usecases

import com.kuzheevadel.core.common.AudioDataType
import com.kuzheevadel.core.common.DestinationType
import com.kuzheevadel.core.common.MediaType
import com.kuzheevadel.core.models.Album
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.core.models.Playlist
import com.kuzheevadel.core.repositories.AudioRepository
import com.kuzheevadel.core.repositories.PlayerMediaRepository
import com.kuzheevadel.core.utils.setAllAudioUnselected
import com.kuzheevadel.core.utils.setAudioState

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FetchAudioDataUseCaseImpl @Inject constructor(
    private val audioRepo: AudioRepository,
    private val playerRepo: PlayerMediaRepository
): FetchAudioUseCase {

    private var currentMediaFlow: StateFlow<MediaType<Audio>> =
        playerRepo.getStateCurrentMediaData()

    override fun getAllAudioList(): List<Audio> =
        audioRepo.getAllAudio()

    override fun getAudioByPosFromAllAudio(position: Int): Audio {
        return audioRepo.getAllAudio()[position]
    }

    override fun getAllAudioFlow(): Flow<List<Audio>> {
        return currentMediaFlow.flatMapLatest { mediaType ->
            when (mediaType) {
                is MediaType.Audio -> {
                    audioRepo.getAudioFlowWithSetState(mediaType.audio)
                }
                else -> {
                    audioRepo.getAudioFlow()
                }
            }
        }
    }

    override fun getDetailAudioFlow(dataType: AudioDataType, audioListPos: Int): Flow<List<Audio>> {
        return currentMediaFlow.flatMapLatest {
            if (it is MediaType.Audio) {
                flow { emit(audioRepo.getAllAlbumsList()[audioListPos]
                    .audioList
                    .setAudioState(it.audio)) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
            } else {
                flow { emit(audioRepo.getAllAlbumsList()[audioListPos]
                    .audioList
                    .setAllAudioUnselected()) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
            }
        }
    }

    override fun getAllAlbumsList(): List<Album> {
        return audioRepo.getAllAlbumsList()
    }

    override fun getAlbumByPosition(position: Int): Album {
        return audioRepo.getAllAlbumsList()[position]
    }

    override fun getAlbumsFlow(): StateFlow<List<Album>> {
        return audioRepo.getAlbumsStateFlow()
    }

    override fun getAllPlaylistsList(): List<Playlist> {
        return audioRepo.getAllPlaylists()
    }

    override fun getPlayListsFlow(): Flow<List<Playlist>> {
        return audioRepo.getPlaylistsFlow()
    }

    override fun getPlaylistByPosition(position: Int): Playlist {
        return audioRepo.getAllPlaylists()[position]
    }

    override fun getAudioByDestType(destType: DestinationType<Nothing>): Audio {
        return when(destType) {
            is DestinationType.AllAudio -> {
                audioRepo.getAllAudio()[destType.audioPos]
            }

            is DestinationType.Album -> {
                audioRepo.getAllAlbumsList()[destType.albumPos].audioList[destType.audioPos]
            }

            is DestinationType.Playlist -> {
                audioRepo.getAllPlaylists()[destType.playlistPos].audioList[destType.playlistPos]
            }
        }
    }
}

interface FetchAudioUseCase {

    fun getDetailAudioFlow(dataType: AudioDataType, audioListPos: Int): Flow<List<Audio>>
    fun getAllAudioList(): List<Audio>
    fun getAudioByPosFromAllAudio(position: Int): Audio
    fun getAllAudioFlow(): Flow<List<Audio>>
    fun getAllAlbumsList(): List<Album>
    fun getAlbumsFlow(): StateFlow<List<Album>>
    fun getAlbumByPosition(position: Int): Album
    fun getAllPlaylistsList(): List<Playlist>
    fun getPlayListsFlow(): Flow<List<Playlist>>
    fun getPlaylistByPosition(position: Int): Playlist
    fun getAudioByDestType(destType: DestinationType<Nothing>): Audio
}