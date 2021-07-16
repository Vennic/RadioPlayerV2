package com.kuzheevadel.radioplayerv2.di

import android.content.Context
import com.kuzheevadel.radioplayerv2.radio.di.RadioComponent
import com.kuzheevadel.radioplayerv2.tracks.di.TracksComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ViewModelBuilderModule::class,
    AppSubcomponents::class,
    RepoModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun getTracksComponent(): TracksComponent.Factory
    fun getRadioComponent(): RadioComponent.Factory
}