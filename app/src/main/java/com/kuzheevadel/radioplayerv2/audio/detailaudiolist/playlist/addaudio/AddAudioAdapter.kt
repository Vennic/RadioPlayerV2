package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.databinding.AddAudioItemBinding
import com.kuzheevadel.radioplayerv2.models.Audio
import javax.inject.Inject

class AddAudioAdapter @Inject constructor(
    diffCallback: AddAudioDiffCallback
): ListAdapter<Audio, RecyclerView.ViewHolder>(diffCallback) {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AudioViewHolder(
            AddAudioItemBinding.inflate(
                inflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val audio = getItem(position)
        (holder as AudioViewHolder).apply {
            tracker?.let {
                bind(audio, it.isSelected(audio.id))
            }
        }
    }

    inner class AudioViewHolder(val binding: AddAudioItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Audio, isActivated: Boolean) {
            binding.apply {
                audio = item

                binding.audioSelectedImage.isVisible = isActivated

                executePendingBindings()
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun inSelectionHotspot(e: MotionEvent): Boolean {
                    return true
                }

                override fun getPosition(): Int {
                    return bindingAdapterPosition
                }
                    override fun getSelectionKey(): Long? {
                        return getItem(bindingAdapterPosition).id
                    }
                }
    }
}

class AddAudioDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Audio>() {

    override fun areItemsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Audio, newItem: Audio): Boolean {
        return oldItem == newItem
    }
}


