package com.kuzheevadel.radioplayerv2.playback.di

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import com.kuzheevadel.radioplayerv2.playback.PlaybackViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PlaybackModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlaybackViewModel::class)
    abstract fun bindViewModel(viewModel: PlaybackViewModel): ViewModel
}