package com.kuzheevadel.radioplayerv2.audio.albums

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.audio.MainTracksFragment
import com.kuzheevadel.radioplayerv2.audio.AudioViewModel
import javax.inject.Inject

class AlbumsFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AudioViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireParentFragment() as MainTracksFragment).tracksComponent
                .inject(this)

        Log.d("ASDF", "Albums viewModel - $viewModel")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.audio_item_layout, container, false)
    }
}