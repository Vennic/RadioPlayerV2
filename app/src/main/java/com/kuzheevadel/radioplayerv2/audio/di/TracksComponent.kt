package com.kuzheevadel.radioplayerv2.audio.di

import com.kuzheevadel.radioplayerv2.audio.albums.AlbumsFragment
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioFragment
import com.kuzheevadel.radioplayerv2.audio.playlist.PlaylistFragment
import dagger.Subcomponent

@AudioFragmentScope
@Subcomponent(modules = [TracksModule::class])
interface TracksComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TracksComponent
    }

    fun inject(fragment: AllAudioFragment)
    fun inject(fragment: AlbumsFragment)
    fun inject(fragment: PlaylistFragment)
}