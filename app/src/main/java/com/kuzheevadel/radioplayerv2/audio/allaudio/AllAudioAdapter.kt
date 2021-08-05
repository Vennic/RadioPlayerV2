package com.kuzheevadel.radioplayerv2.audio.allaudio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.databinding.AudioItemLayoutBinding
import javax.inject.Inject


class AllAudioAdapter @Inject constructor(
        diffCallback: AudioDiffCallback):
        ListAdapter<Audio, RecyclerView.ViewHolder>(diffCallback) {

    lateinit var onSelect: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AudioViewHolder(
                AudioItemLayoutBinding.inflate(
                        inflater, parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val audio = getItem(position)
        (holder as AudioViewHolder).apply {
            bind(audio, position, onSelect)
        }
    }

    class AudioViewHolder(val binding: AudioItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Audio, position: Int, onSelect: (Int) -> Unit) {
            binding.apply {
                audio = item

                root.setOnClickListener {
                    onSelect(position)
                }
                executePendingBindings()
            }
        }
    }
}

class AudioDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Audio>() {

    override fun areItemsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem == newItem
    }
}