package com.kuzheevadel.radioplayerv2.radio.di

import com.kuzheevadel.radioplayerv2.radio.popularradio.PopularRadioFragment
import dagger.Subcomponent

@RadioFragmentScope
@Subcomponent(modules = [RadioModule::class])
interface RadioComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RadioComponent
    }

    fun inject(fragment: PopularRadioFragment)
}