package com.kuzheevadel.radioplayerv2.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.mainPlayer.viewPager2
        val tabLayout = binding.mainPlayer.tabLayout

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All tracks"
                1 -> tab.text = "Albums"
                2 -> tab.text = "Playlist"
                else -> tab.text = "Radio"
            }
        }.attach()
    }
}