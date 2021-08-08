package com.kuzheevadel.radioplayerv2.repositories

import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.common.Constants
import com.kuzheevadel.radioplayerv2.database.AudioDao
import com.kuzheevadel.radioplayerv2.database.PlaylistInfoDao
import com.kuzheevadel.radioplayerv2.models.Album
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.models.PlaylistInfo
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSource
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AudioFragmentScope
class AudioRepositoryImp @Inject constructor(
        private val audioDataSource: AudioDataSource,
        private val audioDao: AudioDao,
        private val playlistInfoDao: PlaylistInfoDao
    ): AudioRepository {

    private var _allAudioList = listOf<Audio>()
    private var albumsList = listOf<Album>()

    private var _currentAlbumsData = MutableStateFlow<List<Album>>(listOf())
    private val currentAlbumsData: StateFlow<List<Album>> = _currentAlbumsData

    //Dispatcher provide!!
    override fun getAudioFlow(): Flow<List<Audio>> =
            audioDataSource.getAudioFlowFromStorage()
                    .onEach {
                        _allAudioList = it
                        _currentAlbumsData.value = createAlbumsList(it)
                        Log.d("TYUI", "getFlow - $albumsList")
                    }
                    .flowOn(Dispatchers.Default)
                    .conflate()

    //Dispatcher provide!!
    override fun getAudioFlowWithSetState(audio: Audio): Flow<List<Audio>> {
        return if (_allAudioList.isNullOrEmpty()) {
            audioDataSource.getAudioFlowFromStorage()
                    .onEach { audioList ->
                        _allAudioList = audioList
                        _currentAlbumsData.value = createAlbumsList(audioList)
                        Log.d("TYUI", "getFlowWithSelected - $albumsList")}
                    .map { audioList ->
                        audioList.setAudioState(audio)
                    }
                    .flowOn(Dispatchers.Default)
                    .conflate()
        } else {
            val newList = _allAudioList.map { it.copy() }

            flow { emit(newList)}
                    .map { audioList ->
                        audioList.setAudioState(audio)
                    }
                    .flowOn(Dispatchers.Default)
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

    override fun getPlaylists(): Flow<List<Playlist>> {
        return playlistInfoDao.getPlaylists()
            .map {
                val playlistList = mutableListOf<Playlist>()

                Log.d("DATATEST", "$it")

                it.onEach { playlistInfo ->
                    val audioList = mutableListOf<Audio>()

                    Log.d("DATATEST", "audioIDlist emty - ${playlistInfo.audioIdList.isNotEmpty()} \n listSize - ${playlistInfo.audioIdList.size}")

                    if (playlistInfo.audioIdList.isNotEmpty()) {
                        playlistInfo.audioIdList.onEach { audioId ->
                            val audioInfo = audioDao.getAudio(audioId.toInt())
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

                    playlistList.add(Playlist(playlistInfo.name, audioList))
                }
                Log.d("DATATEST", "playlistList - $playlistList")
                playlistList
            }
            .flowOn(Dispatchers.Default)
    }

    override suspend fun createPlaylist(name: String) {
        val playlistInfo = PlaylistInfo(name, listOf())
        playlistInfoDao.insertPlaylistInfo(playlistInfo)
    }

    override fun getAlbumAudioList(position: Int): List<Audio> =
        albumsList[position].audioList


    override fun getAlbumsStateFlow(): StateFlow<List<Album>> =
        currentAlbumsData

    override fun getAllAudio(): List<Audio> = _allAudioList

    override fun getAlbum(position: Int): Album = albumsList[position]

}