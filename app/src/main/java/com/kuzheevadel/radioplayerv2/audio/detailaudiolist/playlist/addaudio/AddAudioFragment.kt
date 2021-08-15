package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.audio.AudioNavHostFragment
import com.kuzheevadel.radioplayerv2.databinding.AddAudioFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddAudioFragment: Fragment() {

    private var _binding: AddAudioFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var audioAdapter: AddAudioAdapter

    private val viewModel by viewModels<AddAudioViewModel> { viewModelFactory }

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
        _binding = AddAudioFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.apply {
            addAudioToolbar
                .setupWithNavController(navController, appBarConfiguration)

            addAudioRecycler.layoutManager = LinearLayoutManager(requireContext())
            addAudioRecycler.adapter = audioAdapter
            initRecyclerTracker(addAudioRecycler)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.audioFlow.collect {
                    audioAdapter.submitList(it)
                }
            }
        }
    }

    private fun initRecyclerTracker(recycler: RecyclerView) {
        val tracker = SelectionTracker.Builder(
            "mySelection",
            recycler,
            AudioItemKeyProvider(audioAdapter),
            AudioDetailLookUp(recycler),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        audioAdapter.tracker = tracker
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}