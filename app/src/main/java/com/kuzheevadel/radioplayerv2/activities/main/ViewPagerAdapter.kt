package com.kuzheevadel.radioplayerv2.activities.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzheevadel.radioplayerv2.audio.albums.AlbumsFragment
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioFragment
import com.kuzheevadel.radioplayerv2.audio.playlist.PlaylistFragment

class ViewPagerAdapter(fm: FragmentManager, lf: Lifecycle): FragmentStateAdapter(fm, lf) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 -> AllAudioFragment()
            1 -> AlbumsFragment()
            else -> PlaylistFragment()
        }
    }

}