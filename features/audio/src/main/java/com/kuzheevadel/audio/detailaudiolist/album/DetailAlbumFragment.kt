package com.kuzheevadel.audio.detailaudiolist.album

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.audio.detailaudiolist.BaseDetailAudioFragment
import com.kuzheevadel.core.common.DestinationType

class DetailAlbumFragment: BaseDetailAudioFragment() {

    private val viewModel by viewModels<DetailAlbumAudioViewModel> { viewModelFactory }

    private val args: DetailAlbumFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(args.position, args.title, viewModel, DestinationType.Album())
    }
}