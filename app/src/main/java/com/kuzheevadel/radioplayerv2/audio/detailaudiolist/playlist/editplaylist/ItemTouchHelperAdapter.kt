package com.kuzheevadel.radioplayerv2.audio.detailaudiolist.playlist.editplaylist

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(position: Int)
}