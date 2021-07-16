package com.kuzheevadel.radioplayerv2.activities.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzheevadel.radioplayerv2.tracks.albums.AlbumsFragment
import com.kuzheevadel.radioplayerv2.tracks.alltracks.AllTracksFragment
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.tracks.playlist.PlaylistFragment
import com.kuzheevadel.radioplayerv2.radio.MainRadioFragment

class ViewPagerAdapter(fm: FragmentManager, lf: Lifecycle): FragmentStateAdapter(fm, lf) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> AllTracksFragment()
            1 -> AlbumsFragment()
            else -> PlaylistFragment()
        }
    }

}