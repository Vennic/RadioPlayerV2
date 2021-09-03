package com.kuzheevadel.audio.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.common.AudioConstants
import com.kuzheevadel.audio.common.DatabaseState
import com.kuzheevadel.audio.databinding.ChosePlaylistBottomDialogBinding
import com.kuzheevadel.audio.dialogs.viewmodels.ChosePlaylistBottomViewModel
import com.kuzheevadel.core.common.Constants
import com.kuzheevadel.core.common.DestinationType
import com.kuzheevadel.ui.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChosePlaylistBottomDialogFragment: BaseBottomSheetDialogFragment() {

    private lateinit var binding: ChosePlaylistBottomDialogBinding

    private val args: ChosePlaylistBottomDialogFragmentArgs by navArgs()

    @Inject
    lateinit var playlistAdapter: ChosePlaylistAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ChosePlaylistBottomViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as AudioNavHostFragment).audioComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChosePlaylistBottomDialogBinding.inflate(
            inflater,
            container,
            false
        )

        playlistAdapter.onPlaylistSelect = { playlistPos ->
            when(args.destType) {
                AudioConstants.ALL_AUDIO -> {
                    viewModel.addInPlaylistButtonClicked(
                        DestinationType.AllAudio(args.audioPosition), playlistPos
                    )
                }

                AudioConstants.ALBUM -> {
                    viewModel.addInPlaylistButtonClicked(
                        DestinationType.Album(args.audioPosition, args.albumOrPlaylistPos), playlistPos
                    )
                }
            }

        }

        binding.bottomRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }

        playlistAdapter.submitList(viewModel.getPlaylists())

        subscribeUI()

        return binding.root
    }

    private fun subscribeUI() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.dbState.collect { dbState ->
                    when(dbState) {
                        is DatabaseState.None -> {}
                        is DatabaseState.AudioAdded -> {
                            Log.d("QWER", "added ${dbState.audioName} in ${dbState.playlistName}")
                            Snackbar.make(requireParentFragment().requireView(),
                                "${dbState.audioName} " + getString(R.string.added_in_playlist) +
                                " ${dbState.playlistName}",
                                Snackbar.LENGTH_LONG)
                                .show()
                            findNavController().navigateUp()
                        }

                        is DatabaseState.Error -> {
                            Snackbar.make(requireView(), dbState.error, Snackbar.LENGTH_LONG)
                                .show()
                            findNavController().navigateUp()
                        }
                    }
                }
            }
        }
    }
}