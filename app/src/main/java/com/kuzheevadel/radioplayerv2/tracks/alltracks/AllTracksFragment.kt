package com.kuzheevadel.radioplayerv2.tracks.alltracks

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
import com.google.android.material.snackbar.Snackbar
import com.kuzheevadel.radioplayerv2.common.QueryResult
import com.kuzheevadel.radioplayerv2.databinding.AllTracksLayoutBinding
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import com.kuzheevadel.radioplayerv2.tracks.TracksViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTracksFragment: Fragment() {

    private var _binding: AllTracksLayoutBinding? = null
    private val binding get() = _binding!!
    private val allTracksAdapter = AllTracksAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TracksViewModel> { viewModelFactory }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) {
            isGranted: Boolean ->
            if (isGranted) {
                viewModel.onPermissionGranted()
                Log.d("ASDF", "Permission is granted in callback")
            } else {
                Log.d("ASDF", "Permission is not granted")
                viewModel.onPermissionGranted()
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as PlayerApplication).appComponent
            .getAllTracksComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AllTracksLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allTracksRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = allTracksAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadState.collect { loadState ->
                    when (loadState) {
                        is QueryResult.Success -> {
                            allTracksAdapter.setTrackList(loadState.data)
                            Log.d("ASDF", "Success {${loadState.data}")
                        }
                        is QueryResult.Loading -> {
                            if (loadState.isLoading) {
                                Snackbar.make(view, "Show Progress", Snackbar.LENGTH_SHORT).show()
                            } else {
                                Snackbar.make(view, "Hide progress", Snackbar.LENGTH_SHORT).show()
                            }
                        }
                        is QueryResult.Error -> {
                            Snackbar.make(view, "Error", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        Log.d("ASDF", "All tracks viewModel - $viewModel")
        checkReadStoragePermission()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkReadStoragePermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d("ASDF", "Granted in check")
                viewModel.onPermissionGranted()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                Log.d("ASDF", "Show permission rationale")
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