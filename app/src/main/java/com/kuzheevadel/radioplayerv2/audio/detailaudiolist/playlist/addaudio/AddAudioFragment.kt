package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.R
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
    private val args: AddAudioFragmentArgs by navArgs()
    private lateinit var tracker: SelectionTracker<Long>
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

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
            toolbar = addAudioToolbar

            addAudioToolbar
                .setupWithNavController(navController, appBarConfiguration)

            addAudioToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.apply_add_audio -> {
                        viewModel
                            .addAudioInPlaylist(tracker.selection.toList(), args.playlistPosition)
                        findNavController().navigateUp()
                        true
                    }
                    else -> false
                }
            }

            toolbar.title = "0 songs selected"
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
             tracker = SelectionTracker.Builder(
            "mySelection",
            recycler,
            AudioItemKeyProvider(audioAdapter),
            AudioDetailLookUp(recycler),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        audioAdapter.tracker = tracker

        tracker.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val items = tracker.selection
                    val size = items.size()
                    toolbar.title = "$size songs selected"
                    Log.d("TRACKERTEST", "${tracker.selection.toList()}")
                    binding.addAudioToolbar.menu
                        .findItem(R.id.apply_add_audio).isVisible = size > 0
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}