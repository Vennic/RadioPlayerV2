package com.kuzheevadel.radioplayerv2.audio.di

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.audio.AudioViewModel
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TracksModule {

    @Binds
    @IntoMap
    @ViewModelKey(AudioViewModel::class)
    abstract fun bindViewModel(viewModel: AudioViewModel): ViewModel
}