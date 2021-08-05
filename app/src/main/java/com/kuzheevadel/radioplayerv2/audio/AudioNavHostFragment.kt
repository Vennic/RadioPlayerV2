package com.kuzheevadel.radioplayerv2.audio

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.kuzheevadel.radioplayerv2.audio.di.AudioComponent
import com.kuzheevadel.radioplayerv2.di.PlayerApplication

class AudioNavHostFragment: NavHostFragment() {

    lateinit var audioComponent: AudioComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        audioComponent = (requireActivity().application as PlayerApplication).appComponent
                .getTracksComponent()
                .create()
    }
}