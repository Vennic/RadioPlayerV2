package com.kuzheevadel.radioplayerv2.di

import android.app.Application
import com.kuzheevadel.audio.di.AudioComponent
import com.kuzheevadel.audio.di.AudioComponentProvider
import com.kuzheevadel.audio.di.DaggerAudioComponent
import com.kuzheevadel.core.di.CoreComponent
import com.kuzheevadel.core.di.modules.CoreModule
import com.kuzheevadel.core.di.DaggerCoreComponent
import com.kuzheevadel.playback.di.DaggerPlaybackComponent
import com.kuzheevadel.playback.di.PlaybackComponent
import com.kuzheevadel.playback.di.PlaybackComponentProvider

class PlayerApplication: Application(), AudioComponentProvider, PlaybackComponentProvider {

    lateinit var coreComponent: CoreComponent

    override fun getAudioComponent(): AudioComponent {
        return DaggerAudioComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInject()
    }

    private fun initCoreDependencyInject() {
        coreComponent = DaggerCoreComponent
            .builder()
            .coreModule(CoreModule(this))
            .build()
    }

    override fun getPlaybackComponent(): PlaybackComponent {
        return DaggerPlaybackComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
    }
}