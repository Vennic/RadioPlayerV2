package com.kuzheevadel.radioplayerv2.audio.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.radioplayerv2.MainNavGraphDirections
import com.kuzheevadel.radioplayerv2.databinding.PlaylistItemBottomDialogBinding

class PlaylistItemBottomDialog: BaseBottomSheetDialogFragment() {

    private lateinit var binding: PlaylistItemBottomDialogBinding

    private val args: AudioBottomDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlaylistItemBottomDialogBinding.inflate(inflater, container, false)

        binding.apply {
            addAudioInPlaylistTextView.setOnClickListener {
                val action = MainNavGraphDirections
                    .actionGlobalChosePlaylistBottomDialogFragment(args.audioPosition)
                findNavController().navigateUp()
                findNavController().navigate(action)
            }

            audioInfoTextView.setOnClickListener {
                val action = MainNavGraphDirections
                    .actionGlobalAudioInfoDialogFragment(args.audioPosition)
                findNavController().navigateUp()
                findNavController().navigate(action)
            }

        }

        return binding.root
    }
}