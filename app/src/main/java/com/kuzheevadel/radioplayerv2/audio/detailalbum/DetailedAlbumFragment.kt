package com.kuzheevadel.radioplayerv2.audio.detailalbum

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.audio.AudioNavHostFragment
import com.kuzheevadel.radioplayerv2.audio.MainAudioFragment
import com.kuzheevadel.radioplayerv2.databinding.DetailAlbumBinding
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedAlbumFragment: Fragment() {

    init {
        Log.d("ZXCV", "Detailed init")
    }

    @Inject
    lateinit var audioAdapter: DetailedAlbumAudioAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DetailedAlbumViewModel> { viewModelFactory }

    private var _binding: DetailAlbumBinding? = null
    private val binding get() = _binding!!

    private val args: DetailedAlbumFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as AudioNavHostFragment).audioComponent
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailAlbumBinding.inflate(inflater, container, false)

        viewModel.prepareFlow(args.albumPosition)

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

        binding.detailAlbumRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = audioAdapter
        }

        subscribeUI()
    }

    private fun subscribeUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.audioFLow.collect { albumList ->
                    audioAdapter.setAlbumsList(albumList)
                }
            }
        }
    }
}