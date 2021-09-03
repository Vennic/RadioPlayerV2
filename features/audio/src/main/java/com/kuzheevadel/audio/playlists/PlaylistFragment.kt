package com.kuzheevadel.audio.playlists

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.audio.AudioNavGraphDirections
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.MainAudioFragment
import com.kuzheevadel.audio.MainAudioFragmentDirections
import com.kuzheevadel.audio.databinding.PlaylistFragmentBinding
import com.kuzheevadel.core.common.Constants
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            createPlaylistImageButton.setOnClickListener {
                val action = AudioNavGraphDirections
                    .actionGlobalPlaylistNameDialogFragment(
                        Constants.CREATE_PLAYLIST_RESULT
                    )

                findNavController().navigate(action)
            }
        }

        playlistAdapter.apply {
            onItemMenuButtonClicked = { position, playlistName ->
                val action = MainAudioFragmentDirections
                        .toPlaylistBottomDialogFragment(playlistName, position)

                findNavController().navigate(action)
            }

            onPlaylistSelect = { position, playlistName ->
                val action = MainAudioFragmentDirections
                    .actionMainAudioFragmentToDetailPlaylistFragment(position, playlistName)
                findNavController().navigate(action)
            }
        }

        binding.playlistRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.renamePlaylistData.collect { position ->
                    if (position != null) {
                        val action = AudioNavGraphDirections
                            .actionGlobalPlaylistNameDialogFragment(Constants.RENAME_PLAYLIST_RESULT, position)

                        findNavController().navigate(action)
                    }
                }
            }
        }

        lifecycleScope.launch {
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