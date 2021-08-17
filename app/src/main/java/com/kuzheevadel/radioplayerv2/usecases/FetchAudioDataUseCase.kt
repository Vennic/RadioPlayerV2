package com.kuzheevadel.radioplayerv2.usecases

import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.common.AudioDataType
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.models.Album
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import com.kuzheevadel.radioplayerv2.utils.setAllAudioUnselected
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AudioFragmentScope
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
}