package com.kuzheevadel.audio.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.databinding.PlaylistNameDialogBinding
import com.kuzheevadel.audio.playlists.PlaylistViewModel
import com.kuzheevadel.core.common.Constants
import javax.inject.Inject

class PlaylistNameDialogFragment: DialogFragment() {

    private lateinit var binding: PlaylistNameDialogBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PlaylistViewModel> ({requireParentFragment()}, { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as AudioNavHostFragment).audioComponent
            .inject(this)
    }

    private val args: PlaylistNameDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            binding = PlaylistNameDialogBinding.inflate(inflater, null, false)

            binding.apply {
                playlistName.doAfterTextChanged {
                    binding.playlistTextInput.error = null
                }

                okTextButton.setOnClickListener {
                    val playlistName = binding.playlistName.text.toString()

                    if (playlistName.isNotEmpty()) {
                        createOrUpdatePlaylist(playlistName, args.playlistPos)
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

    private fun createOrUpdatePlaylist(name: String, position: Int = 0) {
        when(args.resultType) {
            Constants.CREATE_PLAYLIST_RESULT -> { viewModel.onCreateNewPlaylist(name)}
            Constants.RENAME_PLAYLIST_POSITION -> { viewModel.onRenamePlaylist(name, position)}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setPlaylistData(null)
    }
}