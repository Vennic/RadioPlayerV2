package com.kuzheevadel.radioplayerv2.radio

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.activities.main.MainActivity
import com.kuzheevadel.radioplayerv2.activities.main.ViewPagerAdapter
import com.kuzheevadel.radioplayerv2.activities.main.ViewPagerRadioAdapter
import com.kuzheevadel.radioplayerv2.databinding.MainRadioLayoutBinding
import com.kuzheevadel.radioplayerv2.databinding.MainTracksLayoutBinding
import com.kuzheevadel.radioplayerv2.di.PlayerApplication
import com.kuzheevadel.radioplayerv2.radio.di.RadioComponent

class MainRadioFragment: Fragment() {

    private var _binding: MainRadioLayoutBinding? = null
    private val binding get() = _binding!!

    lateinit var radioComponent: RadioComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)

        radioComponent = (requireActivity().application as PlayerApplication).appComponent
                .getRadioComponent()
                .create()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = MainRadioLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.radioViewPager
        val tabLayout = binding.mainRadioTabLayout
        val navController = findNavController()

        val drawerLayout = (requireActivity() as MainActivity).drawerLayout

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainTracksFragment, R.id.mainRadioFragment), drawerLayout)
        binding.mainRadioToolbar.setupWithNavController(navController, appBarConfiguration)

        with(viewPager) {
            offscreenPageLimit = 3
            adapter = ViewPagerRadioAdapter(childFragmentManager, lifecycle)
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Popular"
                1 -> tab.text = "Search"
                else -> tab.text = "Favorite"
            }
        }.attach()
    }
}