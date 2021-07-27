package com.kuzheevadel.radioplayerv2.audio.allaudio

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.radioplayerv2.databinding.AllTracksLayoutBinding
import com.kuzheevadel.radioplayerv2.audio.MainTracksFragment
import com.kuzheevadel.radioplayerv2.audio.AudioViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllAudioFragment: Fragment() {

    private var _binding: AllTracksLayoutBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var allAudioAdapter: AllAudioAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AudioViewModel> { viewModelFactory }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) {
            isGranted: Boolean ->
            if (isGranted) {
                subscribeUI()
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as MainTracksFragment).tracksComponent
                .inject(this)
        allAudioAdapter.viewModel = viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllTracksLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.allTracksRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = allAudioAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkReadStoragePermission()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.audioFlow.collect { audioList ->
                    Log.d("XCV", "collect $audioList")
                    allAudioAdapter.submitList(audioList)
                }
            }
        }
    }

    private fun checkReadStoragePermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                subscribeUI()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }
    }
}