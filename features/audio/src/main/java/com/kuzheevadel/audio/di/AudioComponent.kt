package com.kuzheevadel.audio.di

import com.kuzheevadel.audio.albums.AlbumsFragment
import com.kuzheevadel.audio.allaudio.AllAudioFragment
import com.kuzheevadel.audio.detailaudiolist.album.DetailAlbumFragment
import com.kuzheevadel.audio.detailaudiolist.playlist.DetailPlaylistFragment
import com.kuzheevadel.audio.detailaudiolist.playlist.addaudio.AddAudioFragment
import com.kuzheevadel.audio.detailaudiolist.playlist.editplaylist.EditPlaylistFragment
import com.kuzheevadel.audio.dialogs.*
import com.kuzheevadel.audio.playlists.PlaylistFragment
import com.kuzheevadel.core.di.CoreComponent
import com.kuzheevadel.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [
        AudioModule::class,
        ViewModelBuilderModule::class],
    dependencies = [CoreComponent::class]
)
interface AudioComponent {

    fun inject(fragment: AllAudioFragment)
    fun inject(fragment: AlbumsFragment)
    fun inject(fragment: PlaylistFragment)
    fun inject(fragment: DetailAlbumFragment)
    fun inject(fragment: DetailPlaylistFragment)
    fun inject(nameDialogFragment: PlaylistNameDialogFragment)
    fun inject(playlistBottomDialog: PlaylistBottomDialogFragment)
    fun inject(choseBottomDialogFragmentFragment: ChosePlaylistBottomDialogFragment)
    fun inject(dialogFragment: AudioInfoDialogFragment)
    fun inject(fragment: AddAudioFragment)
    fun inject(fragment: EditPlaylistFragment)
    fun inject(fragment: PlaylistItemBottomDialog)
}