package com.kuzheevadel.playback.di

import androidx.lifecycle.ViewModel
import com.kuzheevadel.core.di.scopes.FeatureScope
import com.kuzheevadel.playback.PlaybackViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PlaybackModule {

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(PlaybackViewModel::class)
    abstract fun bindViewModel(viewModel: PlaybackViewModel): ViewModel
}