package com.kuzheevadel.radioplayerv2.activities.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayout
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity: AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var slidingPanel: SlidingUpPanelLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            drawerLayout = mainDrawerLayout
        }
        slidingPanel = binding.mainPlayer.root

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        val navMenu = binding.navMenu

        navMenu.setupWithNavController(navController)

    }

    // Collapse panel when back button pressed if panel expanded
    override fun onBackPressed() {
        when (slidingPanel.panelState) {
            SlidingUpPanelLayout.PanelState.EXPANDED -> {
                slidingPanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                return
            }
            else -> {
                if (navController.currentDestination?.label == "Album") {
                    navController.popBackStack()
                    return
                }
            }
        }

        super.onBackPressed()
    }



}


