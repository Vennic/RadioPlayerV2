package com.kuzheevadel.radioplayerv2.di

import android.app.Application

class PlayerApplication: Application() {

    val appComponent by lazy {
        initializeComponent()
    }

    fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}