package com.kuzheevadel.audio.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.allaudio.AllAudioViewModel
import com.kuzheevadel.audio.common.AudioConstants
import com.kuzheevadel.audio.databinding.AudioInfoDialogBinding
import com.kuzheevadel.audio.dialogs.viewmodels.AudioInfoDialogViewModel
import com.kuzheevadel.core.common.DestinationType

import javax.inject.Inject

class AudioInfoDialogFragment: DialogFragment() {

    private lateinit var binding: AudioInfoDialogBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AudioInfoDialogViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as AudioNavHostFragment).audioComponent
            .inject(this)
    }

    private val args: AudioInfoDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            binding = AudioInfoDialogBinding.inflate(inflater, null, false)

            val destType = createDestType()
            binding.audio = viewModel.getAudio(destType)

            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun createDestType(): DestinationType<Nothing> {
       return when(args.destType) {
           AudioConstants.ALL_AUDIO -> {
                DestinationType.AllAudio(args.audioPosition)
            }

           AudioConstants.ALBUM -> {
               DestinationType.Album(args.audioPosition, args.albumOrPlaylistPos)
           }

           else -> {
               DestinationType.Playlist(args.audioPosition, args.albumOrPlaylistPos)
           }

        }
    }
}