package com.kuzheevadel.radioplayerv2.audio.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.databinding.AlbumItemLayoutBinding
import com.kuzheevadel.radioplayerv2.models.Album
import javax.inject.Inject

class AlbumsAdapter @Inject constructor(): RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private var albumsList = listOf<Album>()

    class AlbumsViewHolder(val binding: AlbumItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album) {
            binding.apply {
                album = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AlbumsViewHolder(
                AlbumItemLayoutBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = albumsList[position]

        (holder as AlbumsViewHolder).apply {
            bind(album)
        }
    }

    override fun getItemCount() = albumsList.size

    fun setAlbumsList(list: List<Album>) {
        albumsList = list
        notifyDataSetChanged()
    }
}