package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.editplaylist

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.audio.albums.AlbumsAdapter
import com.kuzheevadel.radioplayerv2.audio.allaudio.AudioDiffCallback
import com.kuzheevadel.radioplayerv2.databinding.EditAudioItemBinding
import com.kuzheevadel.radioplayerv2.models.Audio
import java.util.*
import javax.inject.Inject

class EditPlaylistAdapter @Inject constructor(
    private val dragStartListener : OnStartDragListener
): RecyclerView.Adapter<EditPlaylistAdapter.AudioViewHolder>(), ItemTouchHelperAdapter {

    private var audioList = mutableListOf<Audio>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AudioViewHolder(
            EditAudioItemBinding.inflate(
                inflater, parent, false
            ), dragStartListener)
    }

    override fun getItemCount(): Int {
        return audioList.size
    }

    class AudioViewHolder(
        val binding: EditAudioItemBinding,
        private val dragStartListener : OnStartDragListener? = null
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Audio) {
            binding.apply {
                dragItemImageView.setOnTouchListener(View.OnTouchListener { v, event ->
                    if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                        dragStartListener?.onStartDrag(this@AudioViewHolder)
                    }
                    false
                })

                deleteAudioImageView.setOnTouchListener(View.OnTouchListener { v, event ->
                    if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                        dragStartListener?.onStartSwipe(this@AudioViewHolder)
                    }
                    false
                })
                audio = item
                executePendingBindings()
            }
        }
    }

    fun setAudioList(list: List<Audio>) {
        audioList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(audioList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        audioList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audio = audioList[position]
        (holder as AudioViewHolder).apply {
            bind(audio)
        }
    }
}


