package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.addaudio

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class AudioDetailLookUp(val recyclerView: RecyclerView
): ItemDetailsLookup<Long>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as AddAudioAdapter.AudioViewHolder).getItemDetails()
        }
        return null
    }

}