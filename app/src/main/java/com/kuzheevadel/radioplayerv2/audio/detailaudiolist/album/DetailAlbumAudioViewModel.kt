package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.album

import android.util.Log
import com.kuzheevadel.radioplayerv2.audio.detailaudiolist.BaseDetailAudioViewModel
import com.kuzheevadel.radioplayerv2.common.DetailAudioItem
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.models.Album
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import com.kuzheevadel.radioplayerv2.utils.setAllAudioUnselected
import com.kuzheevadel.radioplayerv2.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailAlbumAudioViewModel @Inject constructor(
    private val audioRepo: AudioRepository,
    private val playerRepo: PlayerMediaRepository
): BaseDetailAudioViewModel() {

    private lateinit var _audioList: List<Audio>
    private lateinit var _album: Album
    private val _currentMediaFlow = playerRepo.getStateCurrentMediaData()
    private var _albumPos = 0

    override fun getDetailItem(): DetailAudioItem {
        return DetailAudioItem(_album.name, _album.getImageUri())
    }

    override fun init(position: Int) {
        _albumPos = position
        _album = audioRepo.getAlbum(position)
        _audioList = _album.audioList

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