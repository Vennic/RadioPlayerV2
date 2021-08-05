package com.kuzheevadel.radioplayerv2.audio.di

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.audio.albums.AlbumsAdapter
import com.kuzheevadel.radioplayerv2.audio.albums.AlbumsViewModel
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioViewModel
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioAdapter
import com.kuzheevadel.radioplayerv2.audio.allaudio.AudioDiffCallback
import com.kuzheevadel.radioplayerv2.audio.detailalbum.DetailedAlbumViewModel
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryInterface
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSource
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSourceInterface
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AudioModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllAudioViewModel::class)
    abstract fun bindAllAudioViewModel(viewModel: AllAudioViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindAlbumsViewModel(viewModel: AlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailedAlbumViewModel::class)
    abstract fun bindDetailAlbumViewModel(viewModel: DetailedAlbumViewModel): ViewModel

    @Binds
    abstract fun provideAllAudioAdapter(adapter: AllAudioAdapter): ListAdapter<Audio, RecyclerView.ViewHolder>

    @Binds
    abstract fun provideAlbumsAdapter(adapter: AlbumsAdapter): RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>

    @Binds
    abstract fun provideAudioDiffCallback(callback: AudioDiffCallback): DiffUtil.ItemCallback<Audio>

    @Binds
    abstract fun provideAudioRepo(repo: AudioRepository): AudioRepositoryInterface

    @Binds
    abstract fun provideAudioDataSource(dataSource: AudioDataSource): AudioDataSourceInterface
}