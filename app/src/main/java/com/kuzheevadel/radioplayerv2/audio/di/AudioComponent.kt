package com.kuzheevadel.radioplayerv2.audio.di

import com.kuzheevadel.radioplayerv2.audio.albums.AlbumsFragment
import com.kuzheevadel.radioplayerv2.audio.allaudio.AllAudioFragment
import com.kuzheevadel.radioplayerv2.audio.detailalbum.DetailedAlbumFragment
import com.kuzheevadel.radioplayerv2.audio.dialogs.ChosePlaylistBottomDialogFragment
import com.kuzheevadel.radioplayerv2.audio.dialogs.PlaylistBottomDialogFragment
import com.kuzheevadel.radioplayerv2.audio.dialogs.PlaylistNameDialogFragment
import com.kuzheevadel.radioplayerv2.audio.playlists.PlaylistFragment
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
    fun inject(nameDialogFragment: PlaylistNameDialogFragment)
    fun inject(playlistBottomDialog: PlaylistBottomDialogFragment)
    fun inject(choseBottomDialogFragmentFragment: ChosePlaylistBottomDialogFragment)
}