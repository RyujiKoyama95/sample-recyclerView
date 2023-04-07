package com.uminari.samplerecyclerview

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch

class MainFragment(application: Application) : Fragment() {
    companion object {
        private const val TAG = "MainFragment"
    }
    private var itemRepository: ItemRepository
    private var items = listOf<Item>()
    private val latch = CountDownLatch(1)

    init {
        val db = ItemDatabase.getInstance(application)
        val itemDao = db.itemDao()
        itemRepository = ItemRepository(itemDao)
    }

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
//        addItems()
        getItems()
        Log.d(TAG, "onViewCreated step1")
        latch.await()
        recyclerView.apply {
            adapter = ItemAdapter(
                items,
                object : OnItemClickListener {
                    override fun onItemClicked(holder: MainViewHolder) {
                        holder.title.text = "clicked"
                    }
                }
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            Log.d(TAG, "onViewCreated")
        }

        val button = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.container_main_fragment, DialogFragment())
            transaction.commit()
        }
    }

    // get dummy items
    private fun addItems() {
        viewLifecycleOwner.lifecycleScope.launch {
            for (i in 0..10) {
                val item = Item(title = "title_${i}")
                itemRepository.addItem(item)
            }
        }
    }

//    private fun getItems(): List<Item> {
//        var items = listOf<Item>()
//        for (i in 0..10) {
//            val item = Item(title = "${i}行目")
//            items += item
//        }
//        Log.d(TAG, "getItems items=$items")
//        return items
//    }

    private fun getItems() {
        Log.d(TAG, "getItems step2")
        var allItems = listOf<Item>()
        lifecycleScope.launch {
            allItems = itemRepository.getAllItems()
            Log.d(TAG, "getItems step3")
        }
        if (allItems.isNotEmpty()) {
            latch.countDown()
            Log.d(TAG, "getItems step4")
        }
    }
}