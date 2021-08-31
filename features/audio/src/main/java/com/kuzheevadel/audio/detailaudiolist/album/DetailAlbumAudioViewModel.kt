package com.kuzheevadel.audio.detailaudiolist.album

import com.kuzheevadel.audio.detailaudiolist.BaseDetailAudioViewModel
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.SetAudioDataUseCase
import com.kuzheevadel.core.common.AudioDataType
import com.kuzheevadel.core.common.DetailAudioItem
import com.kuzheevadel.core.models.Album
import com.kuzheevadel.core.models.Audio
import javax.inject.Inject

class DetailAlbumAudioViewModel @Inject constructor(
    private val fetchAudioUseCase: FetchAudioUseCase,
    private val setAudioDataUseCase: SetAudioDataUseCase
): BaseDetailAudioViewModel() {

    private lateinit var _audioList: List<Audio>
    private lateinit var _album: Album
    private var _albumPos = 0

    override fun getDetailItem(): DetailAudioItem {
        return DetailAudioItem(_album.name, _album.getImageUri())
    }

    override fun init(position: Int) {
        _albumPos = position
        _album = fetchAudioUseCase.getAlbumByPosition(position)
        _audioList = _album.audioList

        audioFlow = fetchAudioUseCase.getDetailAudioFlow(AudioDataType.ALBUM, position)
    }

    override fun onAudioClicked(position: Int) {
        setAudioDataUseCase.setCurrentAudioData(AudioDataType.ALBUM, position, audioListPos = _albumPos)
    }

    override fun getAudioList(): List<Audio> {
        return _audioList
    }

    override fun updateList(audioList: List<Audio>): List<Audio> {
        return emptyList()
    }
}