package com.kuzheevadel.audio.detailaudiolist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.audio.AudioNavGraphDirections
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.R
import com.kuzheevadel.audio.databinding.DetailAudioListFragmentBinding
import com.kuzheevadel.audio.detailaudiolist.playlist.DetailPlaylistFragmentDirections
import com.kuzheevadel.core.common.Constants
import com.kuzheevadel.core.common.DestinationType

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseDetailAudioFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var audioAdapter: DetailAudioAdapter

    private lateinit var _viewModel: BaseDetailAudioViewModel

    private lateinit var _destinationType: DestinationType<Nothing>

    private var _position: Int = 0

    private var _title = ""

    private var _binding: DetailAudioListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as AudioNavHostFragment).audioComponent
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailAudioListFragmentBinding.inflate(inflater, container, false)

        _viewModel.init(_position)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.item = _viewModel.getDetailItem()

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
                _viewModel.onAudioClicked(position)
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
                _viewModel.audioFlow.collect {
                    audioAdapter.setAlbumsList(it)
                }
            }
        }

        if (_destinationType is DestinationType.Playlist) {
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    _viewModel.playlistFlow.collect {
                        audioAdapter.setAlbumsList(_viewModel.updateList(it[_position].audioList))
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openItemsDialog(position: Int) {
        when(_destinationType) {
            is DestinationType.Album -> {
                val action = AudioNavGraphDirections.actionGlobalAudioBottomDialogFragment(position, _position, Constants.ALBUM)
                findNavController().navigate(action)
            }

            is DestinationType.Playlist -> {

            }

            else -> {}
        }
    }

    private fun setUpAppBarMenu() {
        val addItem = binding.detailAlbumToolbar.menu.findItem(R.id.add_audio)
        val editItem = binding.detailAlbumToolbar.menu.findItem(R.id.edit_playlist)

        if (_destinationType is DestinationType.Album) {
            addItem.isVisible = false
            editItem.isVisible = false
        } else {
            binding.detailAlbumToolbar.setOnMenuItemClickListener{
                when(it.itemId) {
                    R.id.add_audio -> {
                        val action = DetailPlaylistFragmentDirections
                            .actionDetailPlaylistFragmentToAddAudioFragment(_position)
                        findNavController().navigate(action)
                        true
                }
                    R.id.edit_playlist -> {
                        val action = DetailPlaylistFragmentDirections
                            .actionDetailPlaylistFragmentToEditPlaylistFragment(_position)
                        findNavController().navigate(action)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    fun init(
        position: Int,
        title: String,
        viewModel: BaseDetailAudioViewModel,
        destinationType: DestinationType<Nothing>) {
        _position = position
        _title = title
        _viewModel = viewModel
        _destinationType = destinationType
    }
}