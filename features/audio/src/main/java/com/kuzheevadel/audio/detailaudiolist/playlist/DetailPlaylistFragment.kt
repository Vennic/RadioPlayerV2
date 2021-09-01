package com.kuzheevadel.audio.detailaudiolist.playlist

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.audio.detailaudiolist.BaseDetailAudioFragment
import com.kuzheevadel.core.common.DestinationType

class DetailPlaylistFragment: BaseDetailAudioFragment() {

    private val viewModel by viewModels<DetailPlaylistViewModel> { viewModelFactory }

    private val args: DetailPlaylistFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(args.position, args.title, viewModel, DestinationType.Playlist())
    }
}