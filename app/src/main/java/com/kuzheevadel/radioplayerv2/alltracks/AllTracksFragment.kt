package com.kuzheevadel.radioplayerv2.alltracks

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuzheevadel.radioplayerv2.databinding.AllTracksLayoutBinding
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import javax.inject.Inject

class AllTracksFragment: Fragment() {

    private var _binding: AllTracksLayoutBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AllTracksViewModel> { viewModelFactory }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) {
            isGranted: Boolean ->
            if (isGranted) {
                Log.d("ASDF", "Permission is granted in callback")
            } else {
                Log.d("ASDF", "Permission is not granted")
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

        val recycler = binding.allTracksRecycler

        recycler.run {
            layoutManager = LinearLayoutManager(context)
            adapter = AllTracksAdapter()
        }

        Log.d("ASDF", "$viewModel")
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