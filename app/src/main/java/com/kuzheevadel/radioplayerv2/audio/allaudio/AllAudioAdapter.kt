package com.kuzheevadel.radioplayerv2.audio.allaudio

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.models.Audio
import com.kuzheevadel.radioplayerv2.audio.AudioViewModel
import com.kuzheevadel.radioplayerv2.databinding.AudioItemLayoutBinding


class AllAudioAdapter: ListAdapter<Audio, RecyclerView.ViewHolder>(AudioDiffCallback()) {

    lateinit var viewModel: AudioViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AudioViewHolder(
                AudioItemLayoutBinding.inflate(
                        inflater, parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val audio = getItem(position)
        (holder as AudioViewHolder).bind(audio)
    }

    inner class AudioViewHolder(private val binding: AudioItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Audio) {
            binding.apply {
                audio = item
                executePendingBindings()
            }
        }
    }
}

private class AudioDiffCallback : DiffUtil.ItemCallback<Audio>() {

    override fun areItemsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem == newItem
    }
}