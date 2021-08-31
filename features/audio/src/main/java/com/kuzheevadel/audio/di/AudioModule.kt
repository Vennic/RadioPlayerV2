package com.kuzheevadel.audio.di

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.audio.albums.AlbumsAdapter
import com.kuzheevadel.audio.albums.AlbumsViewModel
import com.kuzheevadel.audio.allaudio.AllAudioAdapter
import com.kuzheevadel.audio.allaudio.AllAudioViewModel
import com.kuzheevadel.audio.allaudio.AudioDiffCallback
import com.kuzheevadel.audio.detailaudiolist.DetailAudioAdapter
import com.kuzheevadel.audio.detailaudiolist.album.DetailAlbumAudioViewModel
import com.kuzheevadel.audio.detailaudiolist.playlist.DetailPlaylistViewModel
import com.kuzheevadel.audio.detailaudiolist.playlist.addaudio.AddAudioAdapter
import com.kuzheevadel.audio.detailaudiolist.playlist.addaudio.AddAudioViewModel
import com.kuzheevadel.audio.detailaudiolist.playlist.editplaylist.EditPlaylistViewModel
import com.kuzheevadel.audio.playlists.PlaylistAdapter
import com.kuzheevadel.audio.playlists.PlaylistDiffCallback
import com.kuzheevadel.audio.playlists.PlaylistViewModel
import com.kuzheevadel.audio.usecases.*
import com.kuzheevadel.audio.di.ViewModelKey
import com.kuzheevadel.core.di.scopes.FeatureScope
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.core.models.Playlist
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AudioModule {

    @FeatureScope
    @Binds
    abstract fun provideFetchAudioDataUseCase(useCase: FetchAudioDataUseCaseImpl): FetchAudioUseCase

    @FeatureScope
    @Binds
    abstract fun provideSetAudioDataUseCase(useCase: SetAudioDataUseCaseImpl): SetAudioDataUseCase

    @FeatureScope
    @Binds
    abstract fun provideSaveAudioDataUseCase(useCase: SaveAudioDataUseCaseImpl): SaveAudioDataUseCase

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(AllAudioViewModel::class)
    abstract fun bindAllAudioViewModel(viewModel: AllAudioViewModel): ViewModel

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindAlbumsViewModel(viewModel: AlbumsViewModel): ViewModel

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(PlaylistViewModel::class)
    abstract fun bindPlaylistViewModel(viewModel: PlaylistViewModel): ViewModel

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailAlbumAudioViewModel::class)
    abstract fun bindDetailAlbumViewModel(viewModelAlbum: DetailAlbumAudioViewModel): ViewModel

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailPlaylistViewModel::class)
    abstract fun bindDetailPlaylistViewModel(viewModel: DetailPlaylistViewModel): ViewModel

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(AddAudioViewModel::class)
    abstract fun bindAddAudioViewModel(viewModel: AddAudioViewModel): ViewModel

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(EditPlaylistViewModel::class)
    abstract fun bindEditPlaylistViewModel(viewModel: EditPlaylistViewModel): ViewModel

    @FeatureScope
    @Binds
    abstract fun provideAllAudioAdapter(adapter: AllAudioAdapter): ListAdapter<Audio, RecyclerView.ViewHolder>

    @FeatureScope
    @Binds
    abstract fun provideAlbumsAdapter(adapter: AlbumsAdapter): RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>

    @FeatureScope
    @Binds
    abstract fun provideDetailedAlbumAdapter(adapter: DetailAudioAdapter): RecyclerView.Adapter<DetailAudioAdapter.AudioViewHolder>

    @FeatureScope
    @Binds
    abstract fun provideAudioDiffCallback(callback: AudioDiffCallback): DiffUtil.ItemCallback<Audio>

    @FeatureScope
    @Binds
    abstract fun providePlaylistAdapter(adapter: PlaylistAdapter): ListAdapter<Playlist, RecyclerView.ViewHolder>

    @FeatureScope
    @Binds
    abstract fun providePlaylistDiffCallback(callback: PlaylistDiffCallback): DiffUtil.ItemCallback<Playlist>

    @FeatureScope
    @Binds
    abstract fun provideAddAudioDiffCallback(callback: AudioDiffCallback): DiffUtil.ItemCallback<Audio>

    @FeatureScope
    @Binds
    abstract fun provideAddAudioAdapter(adapter: AddAudioAdapter): ListAdapter<Audio, RecyclerView.ViewHolder>

}