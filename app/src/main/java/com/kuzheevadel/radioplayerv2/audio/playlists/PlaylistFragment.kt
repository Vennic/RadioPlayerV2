package com.kuzheevadel.radioplayerv2.audio.playlists

import android.content.Context
import android.graphics.Color
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.radioplayerv2.audio.MainAudioFragment
import com.kuzheevadel.radioplayerv2.audio.MainAudioFragmentDirections
import com.kuzheevadel.radioplayerv2.common.Constants
import com.kuzheevadel.radioplayerv2.databinding.PlaylistFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistFragment: Fragment() {

    private var _binding: PlaylistFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PlaylistViewModel>{viewModelFactory}

    @Inject
    lateinit var playlistAdapter: PlaylistAdapter

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
        Color.BLACK
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            createPlaylistImageButton.setOnClickListener {
                val action = MainAudioFragmentDirections
                    .toCreatePlaylistDialogFragment(Constants.CREATE_PLAYLIST_RESULT)

                findNavController().navigate(action)
            }
        }

        playlistAdapter.apply {
            onPlaylistSelect = { playlist ->
                viewModel.onDeletePlaylist(playlist.id)
            }
            onRenameButtonClicked = { position ->
                val action = MainAudioFragmentDirections
                        .toCreatePlaylistDialogFragment(Constants.RENAME_PLAYLIST_RESULT, playlistPos = position)

                findNavController().navigate(action)
            }
        }

        binding.playlistRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.playlistData.collect {
                    binding.playlistsCountTextView.text = it.size.toString()
                    playlistAdapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}