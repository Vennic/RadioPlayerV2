package com.kuzheevadel.radioplayerv2.audio.detailalbum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.databinding.AlbumAudioItemBinding
import com.kuzheevadel.radioplayerv2.models.Audio
import javax.inject.Inject

class DetailedAlbumAudioAdapter @Inject constructor(
): RecyclerView.Adapter<DetailedAlbumAudioAdapter.AudioViewHolder>() {

    private var audioList = listOf<Audio>()

    lateinit var onSelect: (Int) -> Unit

    class AudioViewHolder(val binding: AlbumAudioItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Audio, itemPosition: Int, onSelect: (Int) -> Unit) {
            binding.apply {
                audio = item
                position = itemPosition + 1
                executePendingBindings()

                root.setOnClickListener {
                    onSelect(itemPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AudioViewHolder(
                AlbumAudioItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audio = audioList[position]

        holder.bind(audio, position, onSelect)
    }

    override fun getItemCount() = audioList.size

    fun setAlbumsList(list: List<Audio>) {
        audioList = list
        notifyDataSetChanged()
    }
}