package com.kuzheevadel.radioplayerv2.repositories


import android.util.Log
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.common.PlayingState
import com.kuzheevadel.radioplayerv2.models.Audio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerMediaRepositoryImp @Inject constructor(): PlayerMediaRepository {

    private var _currentMediaData = MutableStateFlow<MediaType<Audio>>(MediaType.None(false))
    private val currentMediaData: StateFlow<MediaType<Audio>> = _currentMediaData

    private var _playingState = MutableStateFlow(PlayingState.STOP)
    private val playingState: StateFlow<PlayingState> = _playingState

    private var currentAudioIndex = 0
    private var currentAudioList = listOf<Audio>()

    override fun getMutableCurrentMediaData(): MutableStateFlow<MediaType<Audio>> = _currentMediaData

    override fun getStateCurrentMediaData(): StateFlow<MediaType<Audio>> = currentMediaData

    override fun setCurrentAudioMedia(audioList: List<Audio>, position: Int) {

        if (currentAudioList != audioList) {
            currentAudioList = audioList
        }

        currentAudioIndex = position

        val newAudio = with(currentAudioList[currentAudioIndex]) {
            Audio(uri, name, id, title,artistName, albumTitle, duration, albumId, imageUri, isSelected, isPlaying)
        }

        when (val mediaType = _currentMediaData.value) {
            is MediaType.Audio -> {
                if (mediaType.audio.id == newAudio.id) {
                    newAudio.isSelected = true
                    newAudio.isPlaying = !mediaType.audio.isPlaying
                    setPlayingState(newAudio.isPlaying)
                    _currentMediaData.value = MediaType.Audio(newAudio)

                } else {
                    newAudio.isPlaying = true
                    newAudio.isSelected = true

                    _playingState.value = PlayingState.PLAY
                    _currentMediaData.value = MediaType.Audio(newAudio)
                }
            }
            else -> {
                newAudio.isPlaying = true
                newAudio.isSelected = true

                _playingState.value = PlayingState.PLAY
                _currentMediaData.value = MediaType.Audio(newAudio)
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

    override fun updateAudioList(audioList: List<Audio>) {
        currentAudioList = audioList
    }

    override fun getCurrentAudio(): Audio =
        currentAudioList[currentAudioIndex]


    override fun setNextAudio() {
        if (currentAudioIndex == currentAudioList.size - 1) {
            currentAudioIndex = 0
            setCurrentAudioMedia(currentAudioList, currentAudioIndex)
        } else {
            currentAudioIndex++
            setCurrentAudioMedia(currentAudioList, currentAudioIndex)
        }
    }

    override fun setPreviousAudio() {
        if (currentAudioIndex == 0) {
            currentAudioIndex = currentAudioList.size - 1
            setCurrentAudioMedia(currentAudioList, currentAudioIndex)
        } else {
            currentAudioIndex--
            setCurrentAudioMedia(currentAudioList, currentAudioIndex)
        }
    }

    override fun setRepeatMode(isEnabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setShuffleMode(isEnabled: Boolean) {
        TODO("Not yet implemented")
    }
}