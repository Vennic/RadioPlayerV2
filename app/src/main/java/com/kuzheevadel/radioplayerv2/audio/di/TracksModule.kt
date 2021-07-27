package com.kuzheevadel.radioplayerv2.audio.di

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.audio.AudioViewModel
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioAdapter
import com.kuzheevadel.radioplayerv2.audio.allaudio.AudioDiffCallback
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import com.kuzheevadel.radioplayerv2.models.Audio
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class TracksModule {

    @Binds
    @IntoMap
    @ViewModelKey(AudioViewModel::class)
    abstract fun bindViewModel(viewModel: AudioViewModel): ViewModel

    @Binds
    abstract fun provideAdapter(adapter: AllAudioAdapter): ListAdapter<Audio, RecyclerView.ViewHolder>

    @Binds
    abstract fun provideAudioDiffCallback(callback: AudioDiffCallback): DiffUtil.ItemCallback<Audio>
}