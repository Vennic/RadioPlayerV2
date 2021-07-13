package com.kuzheevadel.radioplayerv2.alltracks

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.databinding.AllTracksLayoutBinding
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import javax.inject.Inject

class AllTracksFragment: Fragment() {

    private var _binding: AllTracksLayoutBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AllTracksViewModel> { viewModelFactory }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}