package com.kuzheevadel.radioplayerv2.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.radioplayerv2.activities.main.MainActivity
import com.kuzheevadel.radioplayerv2.activities.main.ViewPagerAdapter
import com.kuzheevadel.radioplayerv2.databinding.MainTracksLayoutBinding

class MainTracksFragment: Fragment() {

    private var _binding: MainTracksLayoutBinding? = null
    private val binding get() = _binding!!

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
        viewPager.adapter = ViewPagerAdapter(requireActivity())

        val tabLayout = (requireActivity() as MainActivity).tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All tracks"
                1 -> tab.text = "Albums"
                else -> tab.text = "Playlist"
            }
        }.attach()
    }
}