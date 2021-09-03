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
import com.kuzheevadel.audio.databinding.PlaylistItemBottomDialogBinding
import com.kuzheevadel.audio.detailaudiolist.playlist.DetailPlaylistViewModel
import com.kuzheevadel.audio.dialogs.viewmodels.PlaylistItemBottomViewModel
import javax.inject.Inject


class PlaylistItemBottomDialog: BaseBottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PlaylistItemBottomViewModel> { viewModelFactory }

    private lateinit var binding: PlaylistItemBottomDialogBinding

    private val args: PlaylistItemBottomDialogArgs by navArgs()

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
        binding = PlaylistItemBottomDialogBinding.inflate(inflater, container, false)

        binding.apply {
            deleteFromPlaylistTextVew.setOnClickListener {
                viewModel.onDeleteAudioClicked(args.audioPosition, args.albumOrPlaylistPos)
                findNavController().navigateUp()
            }

            audioInfoTextView.setOnClickListener {
                val action = AudioNavGraphDirections
                    .actionGlobalAudioInfoDialogFragment(
                        args.audioPosition,
                        args.albumOrPlaylistPos,
                        args.destType
                    )

                findNavController().navigateUp()
                findNavController().navigate(action)
            }

        }

        return binding.root
    }
}