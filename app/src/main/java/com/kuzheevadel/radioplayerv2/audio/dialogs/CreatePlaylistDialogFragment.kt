package com.kuzheevadel.radioplayerv2.audio.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.radioplayerv2.common.Constants
import com.kuzheevadel.radioplayerv2.databinding.CreatePlaylistDialogBinding

class CreatePlaylistDialogFragment: DialogFragment() {

    private lateinit var binding: CreatePlaylistDialogBinding

    private val args: CreatePlaylistDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            binding = CreatePlaylistDialogBinding.inflate(inflater, null, false)

            binding.apply {
                playlistName.doAfterTextChanged {
                    binding.playlistTextInput.error = null
                }

                okTextButton.setOnClickListener {
                    val playlistName = binding.playlistName.text.toString()

                    if (playlistName.isNotEmpty()) {
                        findNavController().previousBackStackEntry?.savedStateHandle?.set(
                            args.resultType,
                            playlistName
                        )
                        dialog?.cancel()
                    } else {
                        binding.playlistTextInput.error = "Playlist name cannot be empty"
                    }
                }

                cancelTextButton.setOnClickListener {
                    dialog?.cancel()
                }
            }

            builder.setView(binding.root)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}