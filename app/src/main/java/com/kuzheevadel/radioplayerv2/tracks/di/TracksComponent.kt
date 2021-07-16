package com.kuzheevadel.radioplayerv2.tracks.di

import com.kuzheevadel.radioplayerv2.tracks.albums.AlbumsFragment
import com.kuzheevadel.radioplayerv2.tracks.alltracks.AllTracksFragment
import com.kuzheevadel.radioplayerv2.tracks.playlist.PlaylistFragment
import dagger.Subcomponent

@TracksFragmentScope
@Subcomponent(modules = [AllTracksModule::class])
interface TracksComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TracksComponent
    }

    fun inject(fragment: AllTracksFragment)
    fun inject(fragment: AlbumsFragment)
    fun inject(fragment: PlaylistFragment)
}