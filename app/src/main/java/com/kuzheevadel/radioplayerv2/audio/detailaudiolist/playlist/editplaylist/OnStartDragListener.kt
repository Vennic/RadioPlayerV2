package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.editplaylist

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder?)
    fun onStartSwipe(viewHolder: RecyclerView.ViewHolder?)
}