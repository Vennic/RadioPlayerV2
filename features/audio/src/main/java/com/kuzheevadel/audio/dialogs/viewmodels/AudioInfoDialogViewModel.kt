package com.kuzheevadel.audio.dialogs.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kuzheevadel.audio.usecases.FetchAudioUseCase
import com.kuzheevadel.core.common.DestinationType
import javax.inject.Inject

class AudioInfoDialogViewModel @Inject constructor(
    val fetchAudioUseCase: FetchAudioUseCase
): ViewModel() {

    init {
        Log.d("ASD", "$this")
    }
    fun getAudio(destType: DestinationType<Nothing>) =
        fetchAudioUseCase.getAudioByDestType(destType)
}