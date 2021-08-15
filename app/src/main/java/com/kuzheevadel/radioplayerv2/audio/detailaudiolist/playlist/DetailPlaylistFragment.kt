package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.radioplayerv2.audio.detailaudiolist.BaseDetailAudioFragment
import com.kuzheevadel.radioplayerv2.common.DestinationType
import kotlinx.coroutines.launch

class DetailPlaylistFragment: BaseDetailAudioFragment() {

    private val viewModel by viewModels<DetailPlaylistViewModel> { viewModelFactory }

    private val args: DetailPlaylistFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(args.position, args.title, viewModel, DestinationType.PLAYLIST)
    }
}