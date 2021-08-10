package com.kuzheevadel.radioplayerv2.audio.dialogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.audio.playlists.PlaylistDiffCallback
import com.kuzheevadel.radioplayerv2.databinding.PlaylistItemBinding
import com.kuzheevadel.radioplayerv2.models.Playlist
import javax.inject.Inject

class ChosePlaylistAdapter @Inject constructor(
    diffCallback: PlaylistDiffCallback
): ListAdapter<Playlist, RecyclerView.ViewHolder>(diffCallback) {

    lateinit var onPlaylistSelect: (Int) -> Unit

    class PlaylistViewHolder(val binding: PlaylistItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Playlist) {
            binding.apply {
                playlist = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val viewHolder = PlaylistViewHolder(
            PlaylistItemBinding.inflate(
                inflater, parent, false
            )
        )

        viewHolder.binding.apply {
            root.setOnClickListener {
                onPlaylistSelect(viewHolder.adapterPosition)

            }
            playlistMenuImageButton.visibility = View.GONE
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val playlist = getItem(position)
        (holder as PlaylistViewHolder).apply {
            bind(playlist)
        }
    }
}