package com.kuzheevadel.audio.allaudio

import androidx.lifecycle.ViewModel
import com.kuzheevadel.audio.common.DatabaseState
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.audio.usecases.SetAudioDataUseCase
import com.kuzheevadel.core.common.AudioDataType
import com.kuzheevadel.core.models.Audio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AllAudioViewModel @Inject constructor(
    private val fetchAudioDataUseCase: FetchAudioUseCase,
    private val setAudioDataUseCase: SetAudioDataUseCase
): ViewModel() {



    val audioFlow = fetchAudioDataUseCase.getAllAudioFlow()

    fun onAudioClicked(position: Int) {
        setAudioDataUseCase.setCurrentAudioData(AudioDataType.ALL_AUDIO, audioPosition = position)
    }

    fun getAudioByPos(position: Int): Audio {
        return fetchAudioDataUseCase.getAudioByPosFromAllAudio(position)
    }

}