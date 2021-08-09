package com.kuzheevadel.radioplayerv2.audio.detailalbum

import android.content.Context
import android.os.Bundle
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
import com.kuzheevadel.radioplayerv2.audio.AudioNavHostFragment
import com.kuzheevadel.radioplayerv2.databinding.DetailAlbumBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedAlbumFragment: Fragment() {

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

        viewModel.init(args.albumPosition)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.album = viewModel.getAlbum()

        binding.collapsingToolbarLayout
                .setContentScrimColor(android.graphics.Color.BLUE)

        binding.detailAlbumToolbar
                .setupWithNavController(navController, appBarConfiguration)

        binding.detailAlbumRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = audioAdapter
        }

        audioAdapter.onSelect = { position ->
            viewModel.onAudioClicked(position)
        }

        subscribeUI()
    }

    private fun subscribeUI() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.audioFLow.collect { albumList ->
                    audioAdapter.setAlbumsList(albumList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}