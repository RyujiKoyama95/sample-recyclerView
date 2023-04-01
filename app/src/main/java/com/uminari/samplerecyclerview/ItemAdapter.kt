package com.uminari.samplerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class ItemAdapter(
    private val items: ArrayList <Item>,
    private val listener: OnItemClickListener
    ): Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.title.setOnClickListener { listener.onItemClicked(holder) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class MainViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
    var title = itemView.findViewById<TextView>(R.id.list_item_title)!!
//    ↓のtitleを参照しようとするexpecting member declarationでエラー　謎
//    title
}

interface OnItemClickListener {
    fun onItemClicked(holder: MainViewHolder)
}