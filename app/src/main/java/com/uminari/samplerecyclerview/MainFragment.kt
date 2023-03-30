package com.uminari.samplerecyclerview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {
    companion object {
        private const val TAG = "MainFragment"
    }
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.container_recycler_view)
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = MainAdapter(
                getList(),
                listener
            )
        }
    }

    private val listener = object : onItemClickListener {
        override fun onItemClicked(holder: MainAdapter.MainViewHolder, item: Item) {
            item.isDoneStateChanged = !item.isDoneStateChanged
            Log.d(TAG, "onItemClicked status=${item.isDoneStateChanged}")
        }

    }

    private fun getList(): List<Item> {
        val list = mutableListOf<Item>()
        for (i in 0..100) {
            val item = Item()
            item.text = "${i}行目"
            list.add(item)
        }
        return list
    }
}