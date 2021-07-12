package com.kuzheevadel.radioplayerv2.activities.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.radioplayerv2.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager2)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
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