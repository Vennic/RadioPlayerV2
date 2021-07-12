package com.kuzheevadel.radioplayerv2.alltracks.di

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.alltracks.AllTracksViewModel
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AllTracksModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllTracksViewModel::class)
    abstract fun bindViewModel(viewModel: AllTracksViewModel): ViewModel
}