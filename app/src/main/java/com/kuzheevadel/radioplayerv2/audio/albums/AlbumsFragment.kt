package com.kuzheevadel.radioplayerv2.audio.albums

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
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.audio.MainAudioFragment
import com.kuzheevadel.radioplayerv2.databinding.AlbumsLayoutBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumsFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var albumsAdapter: AlbumsAdapter

    private var _binding: AlbumsLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AlbumsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireParentFragment() as MainAudioFragment).audioComponent
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = requireActivity().supportFragmentManager
                .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        albumsAdapter.navController = navController
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AlbumsLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.albumsRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = albumsAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.albumsData.collect {
                    albumsAdapter.setAlbumsList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}