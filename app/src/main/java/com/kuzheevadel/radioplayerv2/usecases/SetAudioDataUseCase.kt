package com.kuzheevadel.radioplayerv2.usecases

import com.kuzheevadel.radioplayerv2.audio.di.AudioFragmentScope
import com.kuzheevadel.radioplayerv2.common.AudioDataType
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.PlayerMediaRepository
import javax.inject.Inject

@AudioFragmentScope
class SetAudioDataUseCaseImpl @Inject constructor(
    private val audioRepo: AudioRepository,
    private val playerRepo: PlayerMediaRepository
): SetAudioDataUseCase {

    override fun setCurrentAudioData(dataType: AudioDataType, audioPosition: Int, audioListPos: Int) {
        when(dataType) {
            AudioDataType.ALL_AUDIO -> {
                playerRepo.setCurrentAudioMedia(audioRepo.getAllAudio(), audioPosition)
            }

            AudioDataType.ALBUM -> { playerRepo.setCurrentAudioMedia(
                audioRepo.getAllAlbumsList()[audioListPos].audioList, audioPosition)
            }

            AudioDataType.PLAYLIST -> { playerRepo.setCurrentAudioMedia(
                audioRepo.getAllPlaylists()[audioListPos].audioList, audioPosition)
            }
        }
    }

}

interface SetAudioDataUseCase {
    fun setCurrentAudioData(
        dataType: AudioDataType,
        audioPosition: Int,
        audioListPos: Int = 0)
}