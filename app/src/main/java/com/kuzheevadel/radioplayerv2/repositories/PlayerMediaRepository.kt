package com.kuzheevadel.radioplayerv2.repositories


import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.common.PlayingState
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.datasource.PlayerMediaRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerMediaRepository @Inject constructor(): PlayerMediaRepositoryInterface {

    private var _currentMediaData = MutableStateFlow<MediaType<Audio>>(MediaType.None(false))
    private val currentMediaData: StateFlow<MediaType<Audio>> = _currentMediaData

    private var _playingState = MutableStateFlow(PlayingState.STOP)
    private val playingState: StateFlow<PlayingState> = _playingState

    override fun getMutableCurrentMediaData(): MutableStateFlow<MediaType<Audio>> = _currentMediaData

    override fun getStateCurrentMediaData(): StateFlow<MediaType<Audio>> = currentMediaData

    override fun setCurrentAudioMedia(audio: Audio) {
        val newAudio = with(audio) {
            Audio(uri, name, id, title,artistName, albumTitle, duration, albumId, imageUri, isSelected, isPlaying)
        }

        when (val mediaType = _currentMediaData.value) {
            is MediaType.Track -> {
                if (mediaType.track.id == newAudio.id) {
                    newAudio.isPlaying = !newAudio.isPlaying
                    setPlayingState(newAudio.isPlaying)
                    _currentMediaData.value = MediaType.Track(newAudio)

                } else {
                    newAudio.isPlaying = true
                    newAudio.isSelected = true

                    _playingState.value = PlayingState.PLAY
                    _currentMediaData.value = MediaType.Track(newAudio)
                }
            }
            else -> {
                newAudio.isPlaying = true
                newAudio.isSelected = true

                _playingState.value = PlayingState.PLAY
                _currentMediaData.value = MediaType.Track(newAudio)
            }
        }

    }

    private fun setPlayingState(isPlaying: Boolean) {
        if (isPlaying) {
            _playingState.value = PlayingState.PLAY
        } else {
            _playingState.value = PlayingState.STOP
        }
    }

    override fun setCurrentMediaList() {
        TODO("Not yet implemented")
    }

    override fun getNextMedia(): Audio {
        TODO("Not yet implemented")
    }

    override fun getPreviousMedia(): Audio {
        TODO("Not yet implemented")
    }

    override fun setRepeatMode(isEnabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setShuffleMode(isEnabled: Boolean) {
        TODO("Not yet implemented")
    }
}