package com.kuzheevadel.radioplayerv2.activities.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzheevadel.radioplayerv2.albums.AlbumsFragment
import com.kuzheevadel.radioplayerv2.alltracks.AllTracksFragment
import com.kuzheevadel.radioplayerv2.playlist.PlaylistFragment
import com.kuzheevadel.radioplayerv2.radio.RadioFragment

class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> AllTracksFragment()
            1 -> AlbumsFragment()
            2 -> PlaylistFragment()
            else -> RadioFragment()
        }
    }
}