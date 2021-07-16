package com.kuzheevadel.radioplayerv2.activities.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    //FIX!!! (Dagger)
    private lateinit var trackPagerAdapter: ViewPagerAdapter
    private lateinit var radioPagerAdapter: ViewPagerRadioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //FIX!!! (Dagger)
        trackPagerAdapter = ViewPagerAdapter(this)
        radioPagerAdapter = ViewPagerRadioAdapter(this)

        binding.navMenu.setNavigationItemSelectedListener(this)
        viewPager2 = binding.mainPlayer.viewPager2
        tabLayout = binding.mainPlayer.tabLayout

        viewPager2.adapter = ViewPagerAdapter(this)

        setViewPager(MediaType.LOCAL_AUDIO, tabLayout, viewPager2)

    }

    //FIX!!!
    private fun setViewPager(mediaType: MediaType,
                             tabLayout: TabLayout,
                             viewPager: ViewPager2) {
        when (mediaType) {
            MediaType.LOCAL_AUDIO -> {
                viewPager.adapter = trackPagerAdapter

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = "All tracks"
                        1 -> tab.text = "Albums"
                        2 -> tab.text = "Playlist"
                        else -> tab.text = "Radio"
                    }
                }.attach()
            }

            MediaType.RADIO -> {
                viewPager.adapter = radioPagerAdapter

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = "Popular"
                        1 -> tab.text = "Search"
                        else -> tab.text = "Favorite"
                    }
                }.attach()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.local_audio -> {
                setViewPager(MediaType.LOCAL_AUDIO, tabLayout, viewPager2)
            }

            R.id.radio -> {
                setViewPager(MediaType.RADIO, tabLayout, viewPager2)
            }
        }

        return true
    }


}