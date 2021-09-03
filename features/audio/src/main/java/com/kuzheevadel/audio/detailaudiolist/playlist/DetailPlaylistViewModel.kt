package com.kuzheevadel.audio.detailaudiolist.playlist

import com.kuzheevadel.audio.detailaudiolist.BaseDetailAudioViewModel
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.EditPlaylistUseCase
import com.kuzheevadel.audio.usecases.SetAudioDataUseCase
import com.kuzheevadel.core.common.AudioDataType
import com.kuzheevadel.core.common.DetailAudioItem
import com.kuzheevadel.core.common.MediaType
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.core.models.Playlist
import com.kuzheevadel.core.repositories.PlayerMediaRepository
import com.kuzheevadel.core.utils.setAllAudioUnselected
import com.kuzheevadel.core.utils.setAudioState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailPlaylistViewModel @Inject constructor(
    private val fetchAudioUseCase: FetchAudioUseCase,
    private val setAudioDataUseCase: SetAudioDataUseCase,
    private val editPlaylistUseCase: EditPlaylistUseCase,
    private val playerRepo: PlayerMediaRepository
): BaseDetailAudioViewModel() {

    private lateinit var _audioList: List<Audio>
    private lateinit var _playlist: Playlist
    private val _currentMediaFlow = playerRepo.getStateCurrentMediaData()
    private var _playlistPos = 0
    private var currentAudio: Audio? = null


    override fun getDetailItem(): DetailAudioItem {
        return DetailAudioItem(_playlist.name, _playlist.getImageUri())
    }

    override fun init(position: Int) {
        _playlistPos = position
        _playlist = fetchAudioUseCase.getPlaylistByPosition(position)
        _audioList = _playlist.audioList
        playlistFlow = fetchAudioUseCase.getPlayListsFlow()

        audioFlow = _currentMediaFlow.flatMapLatest {
            if (it is MediaType.Audio) {
                currentAudio = it.audio
                flow { emit(_audioList.setAudioState(it.audio)) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
            } else {
                currentAudio = null
                flow { emit(_audioList.setAllAudioUnselected()) }
                    .flowOn(Dispatchers.Default)
                    .conflate()
            }
        }
    }

    override fun updateList(audioList: List<Audio>): List<Audio> {
        _audioList = audioList

        return if (currentAudio != null) {
            _audioList.setAudioState(currentAudio!!)
        } else {
            _audioList.setAllAudioUnselected()
        }
    }

    override fun onAudioClicked(position: Int) {
        setAudioDataUseCase.setCurrentAudioData(AudioDataType.PLAYLIST, position, _playlistPos)
    }

    override fun getAudioList(): List<Audio> {
        return _audioList
    }
}