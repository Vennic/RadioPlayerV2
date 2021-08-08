package com.kuzheevadel.radioplayerv2.audio.di

import com.kuzheevadel.radioplayerv2.audio.albums.AlbumsFragment
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioFragment
import com.kuzheevadel.radioplayerv2.audio.detailalbum.DetailedAlbumFragment
import com.kuzheevadel.radioplayerv2.audio.playlist.PlaylistFragment
import dagger.Subcomponent

@AudioFragmentScope
@Subcomponent(modules = [AudioModule::class, AudioDatabaseModule::class])
interface AudioComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AudioComponent
    }

    fun inject(fragment: AllAudioFragment)
    fun inject(fragment: AlbumsFragment)
    fun inject(fragment: PlaylistFragment)
    fun inject(fragment: DetailedAlbumFragment)
}