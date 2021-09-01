package com.kuzheevadel.audio

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.kuzheevadel.audio.di.AudioComponent
import com.kuzheevadel.audio.di.AudioComponentProvider

class AudioNavHostFragment: NavHostFragment() {

    lateinit var audioComponent: AudioComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        audioComponent = (requireActivity().application as AudioComponentProvider).getAudioComponent()
    }
}