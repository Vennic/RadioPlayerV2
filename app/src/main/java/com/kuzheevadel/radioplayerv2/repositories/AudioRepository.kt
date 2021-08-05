package com.kuzheevadel.radioplayerv2.repositories

import android.util.Log
import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.models.Album
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSourceInterface
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AudioFragmentScope
class AudioRepository @Inject constructor(
        private val audioDataSource: AudioDataSourceInterface
    ): AudioRepositoryInterface {

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

    override fun getAlbumAudioList(position: Int): List<Audio> {
        Log.d("TYUI", "getAlbumAudioList - $albumsList")
        return albumsList[position].audioList
    }

    override fun getAlbumsStateFlow(): StateFlow<List<Album>> {
        return currentAlbumsData
    }

    override fun getAllAudio(): List<Audio> {
        return _allAudioList
    }

    override fun getAlbum(position: Int): Album {
       return albumsList[position]
    }

}