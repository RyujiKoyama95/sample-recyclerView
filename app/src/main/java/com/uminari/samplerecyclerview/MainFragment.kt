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
    private lateinit var items: ArrayList<Item>

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
        items = DummyData.getItems()
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = MainAdapter(
                items,
                listener
            )
        }
    }

    private val listener = object : onItemClickListener {
        override fun onItemClicked(holder: MainAdapter.MainViewHolder, item: Item, position: Int) {
            item.isDoneStateChanged = !item.isDoneStateChanged
            if (item.isDoneStateChanged) {
                items.removeAt(position)
                Log.d(TAG, "items=$items")
            }
        }

    }
}