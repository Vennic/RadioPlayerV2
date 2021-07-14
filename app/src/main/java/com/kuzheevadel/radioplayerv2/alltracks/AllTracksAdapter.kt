package com.kuzheevadel.radioplayerv2.alltracks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.models.Track


class AllTracksAdapter: RecyclerView.Adapter<AllTracksAdapter.ViewHolder>() {

    private var trackList = listOf<Track>()

    fun setTrackList(list: List<Track>) {
        trackList = list
        notifyDataSetChanged()
        Log.d("ASDF", "Adapters list $trackList")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.track_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = trackList[position].title
    }

    override fun getItemCount(): Int = trackList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView5)
    }

}