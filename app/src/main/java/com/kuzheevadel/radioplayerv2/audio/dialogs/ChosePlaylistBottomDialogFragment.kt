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
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.radioplayerv2.audio.AudioNavHostFragment
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioViewModel
import com.kuzheevadel.radioplayerv2.databinding.ChosePlaylistBottomDialogBinding
import javax.inject.Inject

class ChosePlaylistBottomDialogFragment: BaseBottomSheetDialogFragment() {

    private lateinit var binding: ChosePlaylistBottomDialogBinding

    private val args: ChosePlaylistBottomDialogFragmentArgs by navArgs()

    @Inject
    lateinit var playlistAdapter: ChosePlaylistAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AllAudioViewModel> ({requireParentFragment()},{ viewModelFactory })

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
            viewModel.addInPlaylistButtonClicked(args.audioPosition, playlistPos)
            findNavController().navigateUp()
        }

        binding.bottomRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playlistAdapter
        }

        playlistAdapter.submitList(viewModel.getPlaylists())

        return binding.root
    }
}