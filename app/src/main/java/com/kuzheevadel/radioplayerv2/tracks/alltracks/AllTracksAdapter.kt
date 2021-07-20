package com.kuzheevadel.radioplayerv2.tracks.alltracks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.BR
import com.kuzheevadel.radioplayerv2.R
import com.kuzheevadel.radioplayerv2.databinding.TrackItemLayoutBinding
import com.kuzheevadel.radioplayerv2.models.Track


class AllTracksAdapter: RecyclerView.Adapter<AllTracksAdapter.ViewHolder>() {

    private var trackList = listOf<Track>()

    fun setTrackList(list: List<Track>) {
        trackList = list
        notifyDataSetChanged()
        Log.d("ASDF", "Adapters list $trackList")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TrackItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.setVariable(BR.track, trackList[position])
    }

    override fun getItemCount(): Int = trackList.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding: TrackItemLayoutBinding? = DataBindingUtil.bind(view)
    }

}