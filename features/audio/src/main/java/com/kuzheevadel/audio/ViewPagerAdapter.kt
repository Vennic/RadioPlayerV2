package com.kuzheevadel.audio

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuzheevadel.audio.albums.AlbumsFragment
import com.kuzheevadel.audio.allaudio.AllAudioFragment
import com.kuzheevadel.audio.playlists.PlaylistFragment

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