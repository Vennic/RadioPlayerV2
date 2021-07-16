package com.kuzheevadel.radioplayerv2.activities.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.common.MediaType
import com.kuzheevadel.radioplayerv2.databinding.ActivityMainBinding
import com.kuzheevadel.radioplayerv2.radio.MainRadioFragment
import com.kuzheevadel.radioplayerv2.tracks.MainTracksFragment

class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fm: FragmentManager

    //FIX!!!
    private val tracksFragment = MainTracksFragment()
    private val mainRadioFragment = MainRadioFragment()

    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        tabLayout = binding.mainPlayer.tabLayout

        fm = supportFragmentManager

        binding.navMenu.setNavigationItemSelectedListener(this)

        fm.beginTransaction()
                .add(R.id.main_container, tracksFragment, "Tracks")
                .commit()
    }

    /*private fun setFragment(fragment: Fragment, fm: FragmentManager, container: Int, name: String) {
        fm.beginTransaction()
                .replace(container, fragment)
                .addToBackStack(name)
                .commit()
    }*/

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.local_audio -> {
                fm.beginTransaction()
                        .add(R.id.main_container, tracksFragment)
                        .commit()
            }

            R.id.radio -> {
                fm.beginTransaction()
                        .add(R.id.main_container, mainRadioFragment)
                        .commit()
            }
        }

        return true
    }


}


