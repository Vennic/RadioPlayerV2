package com.kuzheevadel.radioplayerv2.tracks.di

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.tracks.TracksViewModel
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TracksModule {

    @Binds
    @IntoMap
    @ViewModelKey(TracksViewModel::class)
    abstract fun bindViewModel(viewModel: TracksViewModel): ViewModel
}