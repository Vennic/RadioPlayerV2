package com.kuzheevadel.radioplayerv2.di

import android.content.Context
import com.kuzheevadel.radioplayerv2.playback.PlaybackFragment
import com.kuzheevadel.radioplayerv2.playback.di.PlaybackModule
import com.kuzheevadel.radioplayerv2.radio.di.RadioComponent
import com.kuzheevadel.radioplayerv2.audio.di.AudioComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ViewModelBuilderModule::class,
    PlaybackModule::class,
    AppSubcomponents::class,
    RepoModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun getTracksComponent(): AudioComponent.Factory
    fun getRadioComponent(): RadioComponent.Factory
    fun inject(fragment: PlaybackFragment)
}