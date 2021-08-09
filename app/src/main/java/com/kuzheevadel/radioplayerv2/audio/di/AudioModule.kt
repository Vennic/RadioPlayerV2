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
import com.kuzheevadel.radioplayerv2.audio.detailalbum.DetailedAlbumAudioAdapter
import com.kuzheevadel.radioplayerv2.audio.detailalbum.DetailedAlbumViewModel
import com.kuzheevadel.radioplayerv2.audio.playlists.PlaylistAdapter
import com.kuzheevadel.radioplayerv2.audio.playlists.PlaylistDiffCallback
import com.kuzheevadel.radioplayerv2.audio.playlists.PlaylistViewModel
import com.kuzheevadel.radioplayerv2.di.ViewModelKey
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist
import com.kuzheevadel.radioplayerv2.repositories.AudioRepositoryImp
import com.kuzheevadel.radioplayerv2.repositories.AudioRepository
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSourceImp
import com.kuzheevadel.radioplayerv2.repositories.datasource.AudioDataSource
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
    @ViewModelKey(PlaylistViewModel::class)
    abstract fun bindPlaylistViewModel(viewModel: PlaylistViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailedAlbumViewModel::class)
    abstract fun bindDetailAlbumViewModel(viewModel: DetailedAlbumViewModel): ViewModel

    @Binds
    abstract fun provideAllAudioAdapter(adapter: AllAudioAdapter): ListAdapter<Audio, RecyclerView.ViewHolder>

    @Binds
    abstract fun provideAlbumsAdapter(adapter: AlbumsAdapter): RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>

    @Binds
    abstract fun provideDetailedAlbumAdapter(adapter: DetailedAlbumAudioAdapter): RecyclerView.Adapter<DetailedAlbumAudioAdapter.AudioViewHolder>

    @Binds
    abstract fun provideAudioDiffCallback(callback: AudioDiffCallback): DiffUtil.ItemCallback<Audio>

    @Binds
    abstract fun provideAudioRepo(repo: AudioRepositoryImp): AudioRepository

    @Binds
    abstract fun provideAudioDataSource(dataSourceImp: AudioDataSourceImp): AudioDataSource

    @Binds
    abstract fun providePlaylistAdapter(adapter: PlaylistAdapter): ListAdapter<Playlist, RecyclerView.ViewHolder>

    @Binds
    abstract fun providePlaylistDiffCallback(callback: PlaylistDiffCallback): DiffUtil.ItemCallback<Playlist>

}