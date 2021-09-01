package com.kuzheevadel.audio.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.databinding.ChosePlaylistBottomDialogBinding
import com.kuzheevadel.audio.dialogs.viewmodels.ChosePlaylistBottomViewModel
import com.kuzheevadel.core.common.Constants
import com.kuzheevadel.core.common.DestinationType
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
                Constants.ALL_AUDIO -> {
                    viewModel.addInPlaylistButtonClicked(DestinationType.AllAudio(args.audioPosition), playlistPos)
                    findNavController().navigateUp()
                }

                Constants.ALBUM -> {
                    viewModel.addInPlaylistButtonClicked(DestinationType.Album(args.audioPosition, args.albumOrPlaylistPos), playlistPos)
                    findNavController().navigateUp()
                }
            }

        }

        binding.bottomRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }

        playlistAdapter.submitList(viewModel.getPlaylists())

        return binding.root
    }
}