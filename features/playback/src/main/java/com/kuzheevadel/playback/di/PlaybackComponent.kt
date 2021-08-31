package com.kuzheevadel.playback.di

import androidx.fragment.app.Fragment
import com.kuzheevadel.core.di.CoreComponent
import com.kuzheevadel.core.di.scopes.FeatureScope
import com.kuzheevadel.playback.PlaybackFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [
        PlaybackModule::class,
        ViewModelBuilderModule::class],
    dependencies = [CoreComponent::class]
)

interface PlaybackComponent {
    fun inject(fragment: PlaybackFragment)
}