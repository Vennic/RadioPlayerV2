package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.album

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.radioplayerv2.audio.detailaudiolist.BaseDetailAudioFragment
import com.kuzheevadel.radioplayerv2.common.DestinationType

class DetailAlbumFragment: BaseDetailAudioFragment() {

    private val viewModel by viewModels<DetailAlbumAudioViewModel> { viewModelFactory }

    private val args: DetailAlbumFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(args.position, args.title, viewModel, DestinationType.ALBUM)
    }
}