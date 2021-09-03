package com.kuzheevadel.audio.detailaudiolist.playlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kuzheevadel.audio.AudioNavGraphDirections
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.R
import com.kuzheevadel.audio.common.AudioConstants
import com.kuzheevadel.audio.databinding.DetailPlaylistFragmentBinding
import com.kuzheevadel.core.common.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailPlaylistFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var audioAdapter: DetailPlaylistAudioAdapter

    private var _binding: DetailPlaylistFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailPlaylistViewModel> { viewModelFactory }

    private val args: DetailPlaylistFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as AudioNavHostFragment).audioComponent
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailPlaylistFragmentBinding
            .inflate(inflater, container, false)

        viewModel.init(args.position)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.item = viewModel.getDetailItem()

        binding.collapsingToolbarLayout
            .setContentScrimColor(android.graphics.Color.BLUE)


        binding.detailAlbumToolbar
            .setupWithNavController(navController, appBarConfiguration)

        binding.detailAlbumRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = audioAdapter
        }

        audioAdapter.apply {
            onSelect = { position ->
                viewModel.onAudioClicked(position)
            }

            onMenuButtonClick = { position ->
                openItemsDialog(position)
            }
        }
        setUpAppBarMenu()
        subscribeUI()
    }

    private fun subscribeUI() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.audioFlow.collect {
                    audioAdapter.setAlbumsList(it)
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.playlistFlow.collect {
                    audioAdapter.setAlbumsList(viewModel.updateList(it[args.position].audioList))
                }
            }
        }

        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Int>(AudioConstants.AUDIO_KEY)
            ?.observe(viewLifecycleOwner) { audioCount ->
                Snackbar.make(
                    requireView(),
                    audioCount.toString() + " " +
                            getString(com.kuzheevadel.ui.R.string.songs_added_in_playlist),
                    Snackbar.LENGTH_LONG
                ).show()
            }
    }

    override fun onPause() {
        super.onPause()
        findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.remove<Int>(AudioConstants.AUDIO_KEY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openItemsDialog(position: Int) {
        val action = AudioNavGraphDirections
            .actionGlobalPlaylistItemBottomDialog(position, args.position, AudioConstants.PLAYLIST)
        findNavController().navigate(action)
    }

    private fun setUpAppBarMenu() {

        binding.detailAlbumToolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.add_audio -> {
                    val action = DetailPlaylistFragmentDirections
                        .actionDetailPlaylistFragmentToAddAudioFragment(args.position)
                    findNavController().navigate(action)
                    true
                }
                R.id.edit_playlist -> {
                    val action = DetailPlaylistFragmentDirections
                        .actionDetailPlaylistFragmentToEditPlaylistFragment(args.position)
                    findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }
    }
}