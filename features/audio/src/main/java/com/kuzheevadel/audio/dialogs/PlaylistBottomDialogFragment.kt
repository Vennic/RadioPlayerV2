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
import com.kuzheevadel.audio.AudioNavGraphDirections
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.databinding.PlaylistBottomDialogBinding
import com.kuzheevadel.audio.playlists.PlaylistViewModel
import com.kuzheevadel.core.common.Constants

import javax.inject.Inject

class PlaylistBottomDialogFragment: BaseBottomSheetDialogFragment() {

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
                val action = AudioNavGraphDirections
                    .actionGlobalPlaylistNameDialogFragment(Constants.RENAME_PLAYLIST_POSITION, playlistPos = args.playlistPos)
                findNavController().navigateUp()
                findNavController().navigate(action)
            }

            deletePlaylistTextView.setOnClickListener {
                viewModel.onDeletePlaylist(args.playlistPos)
                dialog?.cancel()
            }
        }

        return binding.root
    }
}