package com.kuzheevadel.radioplayerv2.audio.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kuzheevadel.radioplayerv2.audio.AudioNavHostFragment
import com.kuzheevadel.radioplayerv2.audio.playlists.PlaylistViewModel
import com.kuzheevadel.radioplayerv2.common.Constants
import com.kuzheevadel.radioplayerv2.databinding.PlaylistBottomDialogBinding
import javax.inject.Inject

class PlaylistBottomDialogFragment: BottomSheetDialogFragment() {

    private val args: PlaylistBottomDialogFragmentArgs by navArgs()
    private lateinit var binding: PlaylistBottomDialogBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PlaylistViewModel> ({requireParentFragment()}, { viewModelFactory })

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
        binding = PlaylistBottomDialogBinding.inflate(inflater, container, false)

        binding.apply {
            playlistNameBottomTextView.text = args.playlistName

            renamePlaylistTextView.setOnClickListener {
                findNavController().navigateUp()
                viewModel.setPlaylistData(args.playlistPos)
            }

            deletePlaylistTextView.setOnClickListener {
                viewModel.onDeletePlaylist(args.playlistPos)
                dialog?.cancel()
            }
        }

        return binding.root
    }
}