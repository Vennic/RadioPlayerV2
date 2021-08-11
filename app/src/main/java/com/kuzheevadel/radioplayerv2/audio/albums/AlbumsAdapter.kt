package com.kuzheevadel.radioplayerv2.audio.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.audio.MainAudioFragmentDirections
import com.kuzheevadel.radioplayerv2.databinding.AlbumItemLayoutBinding
import com.kuzheevadel.radioplayerv2.models.Album
import javax.inject.Inject

class AlbumsAdapter @Inject constructor(): RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private var albumsList = listOf<Album>()
    lateinit var navController: NavController

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

        holder.apply {
            bind(album)
            binding.root.setOnClickListener {
                val action = MainAudioFragmentDirections.toDetailedAlbumFragment(position, album.name)
                navController.navigate(action)
            }
        }
    }

    override fun getItemCount() = albumsList.size

    fun setAlbumsList(list: List<Album>) {
        albumsList = list
        notifyDataSetChanged()
    }
}