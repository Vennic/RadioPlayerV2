package com.kuzheevadel.radioplayerv2.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.databinding.ActivityMainBinding
import com.kuzheevadel.radioplayerv2.services.PlayerService
import com.kuzheevadel.ui.DrawableActivity
import com.kuzheevadel.ui.SlidePanelActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout

class MainActivity: AppCompatActivity(), DrawableActivity, SlidePanelActivity{

    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var slidingPanel: SlidingUpPanelLayout
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            drawerLayout = mainDrawerLayout
        }
        slidingPanel = binding.mainPlayer.root

        appBarConfiguration = AppBarConfiguration(setOf(R.id.audio_nav_graph), drawerLayout)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        val navMenu = binding.navMenu

        navMenu.setupWithNavController(navController)

        Intent(this, PlayerService::class.java).also { intent ->
            startService(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun getAppBarConfig(): AppBarConfiguration {
        return appBarConfiguration
    }

    override fun getSlidePanelLayoutInst(): SlidingUpPanelLayout =
        slidingPanel

}


