package com.kuzheevadel.radioplayerv2.audio.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.databinding.PlaylistItemBinding
import com.kuzheevadel.radioplayerv2.models.Playlist
import javax.inject.Inject

class PlaylistAdapter @Inject constructor(
    diffCallback: PlaylistDiffCallback
): ListAdapter<Playlist, RecyclerView.ViewHolder>(diffCallback) {

    lateinit var onPlaylistSelect: (Int, String) -> Unit
    lateinit var onItemMenuButtonClicked: (Int, String) -> Unit

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

        viewHolder.binding.root.setOnClickListener {
            onPlaylistSelect(viewHolder.adapterPosition, currentList[viewHolder.adapterPosition].name)

        }

        viewHolder.binding.playlistMenuImageButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemMenuButtonClicked(position, currentList[position].name)
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

class PlaylistDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Playlist>() {
    override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
        return oldItem == newItem
    }


}