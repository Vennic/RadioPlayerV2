package com.kuzheevadel.radioplayerv2.audio.playlists

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.radioplayerv2.audio.MainAudioFragment
import com.kuzheevadel.radioplayerv2.databinding.PlaylistFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class PlaylistFragment: Fragment() {

    private var _binding: PlaylistFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PlaylistViewModel> { viewModelFactory }

    private val adapterAS = PlaylistAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as MainAudioFragment).audioComponent
                .inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = PlaylistFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            createPlaylistImageButton.setOnClickListener {
                viewModel.onCreateNewPlaylist("Adel ${Random(1000)}")
            }
        }

        adapterAS.onSelect = { playlist ->
            viewModel.onDeletePlaylist(playlist.name)
        }

        binding.playlistRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterAS
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.playlistData.collect {
                    binding.playlistsCountTextView.text = it.size.toString()
                    adapterAS.setPlaylistList(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}