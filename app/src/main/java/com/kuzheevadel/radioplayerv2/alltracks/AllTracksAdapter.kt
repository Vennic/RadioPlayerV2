package com.kuzheevadel.radioplayerv2.alltracks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.radioplayerv2.R


class AllTracksAdapter: RecyclerView.Adapter<AllTracksAdapter.ViewHolder>() {


    private val testList = mutableListOf<String>()

    init {
        repeat(100) {
            testList.add("Number $it")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.track_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = testList[position]
    }

    override fun getItemCount(): Int = testList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView5)
    }

}