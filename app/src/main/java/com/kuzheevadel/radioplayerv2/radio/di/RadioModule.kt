package com.kuzheevadel.radioplayerv2.radio.di

import androidx.lifecycle.ViewModel
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import com.kuzheevadel.radioplayerv2.radio.RadioViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RadioModule {

    @Binds
    @IntoMap
    @ViewModelKey(RadioViewModel::class)
    abstract fun bindViewModel(viewModel: RadioViewModel): ViewModel
}