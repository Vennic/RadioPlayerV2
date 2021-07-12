package com.kuzheevadel.radioplayerv2.alltracks

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
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import javax.inject.Inject

class AllTracksFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AllTracksViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as PlayerApplication).appComponent
            .getAllTracksComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_tracks_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ASDF", "$viewModel")
    }
}