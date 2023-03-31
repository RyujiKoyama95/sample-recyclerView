package com.uminari.samplerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val list: List<Item>,
    private val listener: onItemClickListener
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textView.text = list[position].text
        val item = list[position]
        holder.checkBox.setOnClickListener { listener.onItemClicked(holder, item, position) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.list_item_text_view)
        val checkBox: CheckBox = itemView.findViewById(R.id.list_item_checkBox)
    }
}

interface onItemClickListener {
    fun onItemClicked(holder: MainAdapter.MainViewHolder, item: Item, position: Int)
}