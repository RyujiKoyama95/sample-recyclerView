package com.uminari.samplerecyclerview

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainFragment(application: Application) : Fragment() {
    private lateinit var itemRepository: ItemRepository
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
        val items = getItems()
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
        }

        val button = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.replace(R.id.container_main_fragment, DialogFragment())
            transaction.commit()
        }
    }

    private fun getItems(): List<Item> {
        var items = listOf<Item>()
        viewLifecycleOwner.lifecycleScope.launch {
            for (i in 0..10) {
                val item = Item(i, "title_${i}")
                itemRepository.addItem(item)
            }
            items = itemRepository.getAllItems()
        }
        return items
    }
}