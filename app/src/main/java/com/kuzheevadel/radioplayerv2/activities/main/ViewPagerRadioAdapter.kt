package com.kuzheevadel.radioplayerv2.activities.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzheevadel.radioplayerv2.radio.popularradio.PopularRadioFragment

class ViewPagerRadioAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle)  {
    override fun getItemCount(): Int = 3

    //FIX!!!
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularRadioFragment()
            1 -> PopularRadioFragment()
            else -> PopularRadioFragment()
        }
    }
}