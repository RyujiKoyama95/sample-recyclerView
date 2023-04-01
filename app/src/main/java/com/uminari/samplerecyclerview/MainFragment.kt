package com.uminari.samplerecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.container_recycler_view)
        val items = getItems()
        recyclerView.apply {
            adapter = ItemAdapter(
                items,
                object : OnItemClickListener {
                    override fun onItemClicked(holder: MainViewHolder) {
                        holder.title.text = "done"
                    }
                }
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getItems(): ArrayList<Item> {
        var items = arrayListOf<Item>()
        for (i in 0..10) {
            val item = Item()
            item.title = "${i}行目"
            items.add(item)
        }
        return items
    }
}