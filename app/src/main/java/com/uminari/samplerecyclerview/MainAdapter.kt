package com.uminari.samplerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uminari.samplerecyclerview.databinding.ListItemBinding

class MainAdapter(
    private val list: List<Item>,
    private val listener: onItemClickListener
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        holder.textView.text = list[position].text
//        val item = list[position]
//        holder.checkBox.setOnClickListener { listener.onItemClicked(holder, item, position) }

        // viewにItemをbindする
        val item = list[position]
        holder.bindObjectToView(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MainViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
//        val textView: TextView = itemView.findViewById(R.id.list_item_text_view)
//        val checkBox: CheckBox = itemView.findViewById(R.id.list_item_checkBox)
        fun bindObjectToView(item: Item) {
            binding.item = item
        }
    }
}

interface onItemClickListener {
    fun onItemClicked(holder: MainAdapter.MainViewHolder, item: Item, position: Int)
}