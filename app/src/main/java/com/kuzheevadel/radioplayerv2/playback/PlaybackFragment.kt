package com.kuzheevadel.radioplayerv2.playback

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import javax.inject.Inject

class PlaybackFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PlaybackViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as PlayerApplication).appComponent
                .inject(this)
    }
}