package com.kuzheevadel.radioplayerv2.audio.detailalbum

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.kuzheevadel.radioplayerv2.databinding.DetailAlbumBinding
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import javax.inject.Inject

class DetailedAlbumFragment: Fragment() {

    init {
        Log.d("ZXCV", "Detailed init")
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DetailedAlbumViewModel> { viewModelFactory }

    private var _binding: DetailAlbumBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as PlayerApplication).appComponent
                .getTracksComponent()
                .create()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailAlbumBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.collapsingToolbarLayout.apply {
            title = "Reise Reise"
            setContentScrimColor(android.graphics.Color.BLUE)
        }

        val navController = findNavController()

        Log.d("NavTest", "DetailedAlbumFragment - $navController, \n id - ${navController.currentDestination?.id}, " +
                "navigatorName - ${navController.currentDestination?.navigatorName}, lable - ${navController.currentDestination?.label}")
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.detailAlbumToolbar.setupWithNavController(navController, appBarConfiguration)
    }
}