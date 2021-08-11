package com.kuzheevadel.radioplayerv2.audio.detailaudiolist

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
import com.kuzheevadel.radioplayerv2.audio.AudioNavHostFragment
import com.kuzheevadel.radioplayerv2.databinding.DetailAudioListFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseDetailAudioFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var audioAdapter: DetailAudioAdapter

    private lateinit var _viewModel: BaseDetailAudioViewModel

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

        audioAdapter.onSelect = { position ->
            _viewModel.onAudioClicked(position)
        }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun init(position: Int, title: String, viewModel: BaseDetailAudioViewModel) {
        _position = position
        _title = title
        _viewModel = viewModel
    }

}