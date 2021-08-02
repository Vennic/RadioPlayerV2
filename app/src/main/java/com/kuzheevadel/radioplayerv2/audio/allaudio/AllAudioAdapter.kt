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

    lateinit var viewModel: AllAudioViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AudioViewHolder(
                AudioItemLayoutBinding.inflate(
                        inflater, parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val audio = getItem(position)
        (holder as AudioViewHolder).apply {
            bind(audio)

            binding.root.setOnClickListener {
                viewModel.onTrackClicked(position)
            }
        }
    }

    inner class AudioViewHolder(val binding: AudioItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Audio) {
            binding.apply {
                audio = item
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