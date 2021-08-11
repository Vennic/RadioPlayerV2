package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.album

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.radioplayerv2.audio.detailaudiolist.BaseDetailAudioFragment

class DetailAlbumFragment: BaseDetailAudioFragment() {

    private val viewModel by viewModels<DetailAlbumAudioViewModel> { viewModelFactory }

    private val args: DetailAlbumFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(args.position, args.title, viewModel)
    }
}