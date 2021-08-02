package com.kuzheevadel.radioplayerv2.audio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.radioplayerv2.activities.main.MainActivity
import com.kuzheevadel.radioplayerv2.activities.main.ViewPagerAdapter
import com.kuzheevadel.radioplayerv2.databinding.MainTracksLayoutBinding
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import com.kuzheevadel.radioplayerv2.audio.di.TracksComponent

class MainTracksFragment: Fragment() {

    private var _binding: MainTracksLayoutBinding? = null
    private val binding get() = _binding!!

    lateinit var tracksComponent: TracksComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)

        tracksComponent = (requireActivity().application as PlayerApplication).appComponent
                .getTracksComponent()
                .create()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = MainTracksLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.tracksViewPager

        with(viewPager) {
            offscreenPageLimit = 3
            adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        }

        val tabLayout = (requireActivity() as MainActivity).tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All tracks"
                1 -> tab.text = "Albums"
                else -> tab.text = "Playlist"
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ASDC", "MainTracksFragment onDestroy")
    }
}