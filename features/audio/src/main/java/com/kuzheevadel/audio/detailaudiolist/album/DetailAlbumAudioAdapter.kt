package com.kuzheevadel.audio.detailaudiolist.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.audio.databinding.AlbumAudioItemBinding
import com.kuzheevadel.core.models.Audio

import javax.inject.Inject

class DetailAlbumAudioAdapter @Inject constructor()
    : RecyclerView.Adapter<DetailAlbumAudioAdapter.AudioViewHolder>() {

    private var audioList = listOf<Audio>()

    lateinit var onSelect: (Int) -> Unit
    lateinit var onMenuButtonClick: (Int) -> Unit

    class AudioViewHolder(val binding: AlbumAudioItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Audio, itemPosition: Int) {
            binding.apply {
                audio = item
                position = itemPosition + 1
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = AudioViewHolder(
                AlbumAudioItemBinding.inflate(inflater, parent, false)
        )

        viewHolder.binding.root.setOnClickListener {
            onSelect(viewHolder.bindingAdapterPosition)
        }
        viewHolder.binding.menuButtonImageView.setOnClickListener {
            onMenuButtonClick(viewHolder.bindingAdapterPosition)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audio = audioList[position]
        holder.bind(audio, position)
    }

    override fun getItemCount() = audioList.size

    fun setAlbumsList(list: List<Audio>) {
        audioList = list
        notifyDataSetChanged()
    }
}