package com.kuzheevadel.radioplayerv2.repositories

import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.common.Constants
import com.kuzheevadel.radioplayerv2.database.PlaylistAudioDao
import com.kuzheevadel.radioplayerv2.models.*
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSource
import com.kuzheevadel.radioplayerv2.utils.mapToAudioEntity
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AudioFragmentScope
class AudioRepositoryImp @Inject constructor(
    private val audioDataSource: AudioDataSource,
    private val playlistAudioDao: PlaylistAudioDao,
    private val defDispatcher: CoroutineDispatcher
    ): AudioRepository {

    private var _allAudioList = emptyList<Audio>()
    private var albumsList = emptyList<Album>()
    private var _playlistList = emptyList<Playlist>()
    private var _playlistInfoList = emptyList<PlaylistInfo>()

    private var _currentAlbumsData = MutableStateFlow<List<Album>>(listOf())
    private val currentAlbumsData: StateFlow<List<Album>> = _currentAlbumsData

    override fun getAudioFlow(): Flow<List<Audio>> =
            audioDataSource.getAudioFlowFromStorage()
                    .onEach {
                        _allAudioList = it
                        _currentAlbumsData.value = createAlbumsList(it)
                        playlistAudioDao.deleteAndInsertAudio(_allAudioList.mapToAudioEntity())
                    }
                    .flowOn(defDispatcher)
                    .conflate()

    override fun getAudioFlowWithSetState(audio: Audio): Flow<List<Audio>> {
        return if (_allAudioList.isNullOrEmpty()) {
            audioDataSource.getAudioFlowFromStorage()
                    .onEach { audioList ->
                        _allAudioList = audioList
                        _currentAlbumsData.value = createAlbumsList(audioList)
                    }
                    .map { audioList ->
                        audioList.setAudioState(audio)
                    }
                    .flowOn(defDispatcher)
                    .conflate()
        } else {
            val newList = _allAudioList.map { it.copy() }

            flow { emit(newList)}
                    .map { audioList ->
                        audioList.setAudioState(audio)
                    }
                    .flowOn(defDispatcher)
                    .conflate()
        }
    }

    override fun createAlbumsList(audioList: List<Audio>): List<Album> {
        val albumsMap = mutableMapOf<String, MutableList<Audio>>()
        val albumList = mutableListOf<Album>()

        for (item in audioList) {
            if (!albumsMap.containsKey(item.albumTitle)) {
                albumsMap[item.albumTitle] = mutableListOf(item)
            } else {
                albumsMap.getValue(item.albumTitle).add(item)
            }
        }

        for (item in albumsMap.entries) {
            val album = Album(item.key, item.value)
            albumList.add(album)
        }

        albumsList = albumList
        return albumList
    }

    override fun getPlaylistsFlow(): Flow<List<Playlist>> {
        return playlistAudioDao.getPlaylists()
            .map {
                _playlistInfoList = it
                val playlistList = mutableListOf<Playlist>()

                it.onEach { playlistInfo ->
                    val audioList = mutableListOf<Audio>()

                    if (playlistInfo.audioIdList.isNotEmpty()) {
                        playlistInfo.audioIdList.onEach { audioId ->
                            val audioInfo = playlistAudioDao.getAudio(audioId.toInt())

                            val contentUri: Uri = ContentUris.withAppendedId(
                                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                audioInfo.id
                            )

                            val imageUri: Uri = ContentUris.withAppendedId(
                                Uri.parse(Constants.BASE_ALBUMSART_URI), audioInfo.albumId
                            )

                            val audio = with(audioInfo) {
                                Audio(
                                    contentUri,
                                    name,
                                    id,
                                    title,
                                    artistName,
                                    albumTitle,
                                    duration,
                                    albumId,
                                    imageUri
                                )
                            }

                            audioList.add(audio)
                        }
                    }

                    playlistList.add(Playlist(playlistInfo.id, playlistInfo.name, audioList))
                }

                _playlistList = playlistList
                playlistList
            }
            .flowOn(defDispatcher)
    }

    override suspend fun createPlaylist(name: String) {
        val playlistInfo = PlaylistInfo(generateId(), name, listOf())
        playlistAudioDao.insertPlaylistInfo(playlistInfo)
    }

    override suspend fun renamePlaylist(newName: String, position: Int) {
        val renamedPlaylistInfo = with(_playlistInfoList[position]) {
            PlaylistInfo(id, newName, audioIdList)
        }
        playlistAudioDao.updatePlaylistInfo(renamedPlaylistInfo)
    }

    override suspend fun deletePlaylist(position: Int) {
        val id = _playlistInfoList[position].id
        playlistAudioDao.deletePlaylistInfo(id)
    }

    override suspend fun addAudioInPlaylist(audio: Audio, playlist: Playlist) {

        if (!playlist.audioList.contains(audio)) {
            val audioIdList = playlist.audioList.map { it.id.toString() }.toMutableList()
            audioIdList.add(audio.id.toString())
            val playlistInfo = PlaylistInfo(playlist.id, playlist.name, audioIdList)

            playlistAudioDao.updatePlaylistInfo(playlistInfo)
        }
    }

    override fun getPlaylistByPosition(position: Int): Playlist =
        _playlistList[position]

    override fun getAllPlaylists(): List<Playlist> = _playlistList

    override fun getAlbumAudioList(position: Int): List<Audio> =
        albumsList[position].audioList

    override fun getAlbumsStateFlow(): StateFlow<List<Album>> =
        currentAlbumsData

    override fun getAllAudio(): List<Audio> = _allAudioList

    override fun getAlbum(position: Int): Album = albumsList[position]

}
