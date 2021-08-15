package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist

import android.util.Log
import com.kuzheevadel.radioplayerv2.audio.detailaudiolist.BaseDetailAudioViewModel
import com.kuzheevadel.radioplayerv2.common.DetailAudioItem
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import com.kuzheevadel.radioplayerv2.utils.setAllAudioUnselected
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailPlaylistViewModel @Inject constructor(
    private val audioRepo: AudioRepository,
    private val playerRepo: PlayerMediaRepository
): BaseDetailAudioViewModel() {

    private lateinit var _audioList: List<Audio>
    private lateinit var _playlist: Playlist
    private val _currentMediaFlow = playerRepo.getStateCurrentMediaData()
    private var _playlistPos = 0

    override fun getDetailItem(): DetailAudioItem {
        return DetailAudioItem(_playlist.name, _playlist.getImageUri())
    }

    override fun init(position: Int) {
        _playlistPos = position
        _playlist = audioRepo.getPlaylistByPosition(position)
        _audioList = _playlist.audioList

        audioFlow = _currentMediaFlow.flatMapLatest {
            if (it is MediaType.Audio) {
                flow { emit(_audioList.setAudioState(it.audio)) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
            } else {
                flow { emit(_audioList.setAllAudioUnselected()) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
            }
        }
    }

    override fun onAudioClicked(position: Int) {
        playerRepo.setCurrentAudioMedia(_audioList, position)
    }

    override fun getAudioList(): List<Audio> {
        return _audioList
    }
}