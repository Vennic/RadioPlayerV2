package com.kuzheevadel.audio.detailaudiolist.album

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
import com.kuzheevadel.audio.AudioNavGraphDirections
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.common.AudioConstants
import com.kuzheevadel.audio.databinding.DetailAlbumFragmentBinding
import com.kuzheevadel.core.common.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailAlbumFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var audioAdapter: DetailAlbumAudioAdapter

    private var _binding: DetailAlbumFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailAlbumAudioViewModel> { viewModelFactory }

    private val args: DetailAlbumFragmentArgs by navArgs()

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

        _binding = DetailAlbumFragmentBinding
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

        subscribeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUI() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.audioFlow.collect {
                    audioAdapter.setAlbumsList(it)
                }
            }
        }
    }

    private fun openItemsDialog(position: Int) {
        val action = AudioNavGraphDirections
            .actionGlobalAudioBottomDialogFragment(position, args.position, AudioConstants.ALBUM)
        findNavController().navigate(action)
    }
}