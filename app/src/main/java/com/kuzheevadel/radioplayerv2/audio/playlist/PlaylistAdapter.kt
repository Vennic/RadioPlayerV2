package com.kuzheevadel.radioplayerv2.audio.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.databinding.AlbumAudioItemBinding
import com.kuzheevadel.radioplayerv2.databinding.PlaylistItemBinding
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.models.Playlist

class PlaylistAdapter () : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var playlistList = listOf<Playlist>()

    lateinit var onSelect: (Int) -> Unit

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
                PlaylistItemBinding.inflate(inflater, parent, false)
        )

        viewHolder.binding.root.setOnClickListener {
            onSelect(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlistList[position]
        holder.bind(playlist)
    }

    override fun getItemCount() = playlistList.size

    fun setAlbumsList(list: List<Playlist>) {
        playlistList = list
        notifyDataSetChanged()
    }
}