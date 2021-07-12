package com.kuzheevadel.radioplayerv2.alltracks.di

import com.kuzheevadel.radioplayerv2.alltracks.AllTracksFragment
import dagger.Subcomponent

@Subcomponent(modules = [AllTracksModule::class])
interface AllTracksComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AllTracksComponent
    }

    fun inject(fragment: AllTracksFragment)
}