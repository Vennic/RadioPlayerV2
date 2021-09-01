package com.kuzheevadel.audio.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.audio.AudioNavGraphDirections
import com.kuzheevadel.audio.databinding.PlaylistItemBottomDialogBinding


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
            /*addAudioInPlaylistTextView.setOnClickListener {
                val action = AudioNavGraphDirections
                    .actionGlobalChosePlaylistBottomDialogFragment(args.audioPosition)
                findNavController().navigateUp()
                findNavController().navigate(action)
            }*/

            audioInfoTextView.setOnClickListener {
                val action = AudioNavGraphDirections
                    .actionGlobalAudioInfoDialogFragment(args.audioPosition)
                findNavController().navigateUp()
                findNavController().navigate(action)
            }

        }

        return binding.root
    }
}