package com.kuzheevadel.audio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.audio.R
import com.kuzheevadel.audio.databinding.MainAudioLayoutBinding
import com.kuzheevadel.audio.di.AudioComponent
import com.kuzheevadel.audio.di.AudioComponentProvider
import com.kuzheevadel.ui.DrawableActivity

class MainAudioFragment: Fragment() {

    private var _binding: MainAudioLayoutBinding? = null
    private val binding get() = _binding!!

    lateinit var audioComponent: AudioComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)

        audioComponent = (requireParentFragment() as AudioNavHostFragment).audioComponent
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = MainAudioLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val appBarConfiguration = (requireActivity() as DrawableActivity).getAppBarConfig()
        binding.mainAudioToolbar.setupWithNavController(navController, appBarConfiguration)

        val viewPager = binding.audioViewPager

        with(viewPager) {
            offscreenPageLimit = 3
            adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        }

        TabLayoutMediator(binding.mainAudioTabLayout, viewPager) { tab, position ->
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