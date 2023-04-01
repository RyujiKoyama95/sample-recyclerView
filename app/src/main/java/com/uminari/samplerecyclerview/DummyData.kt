package com.uminari.samplerecyclerview

import java.util.ArrayList

object DummyData {
    fun getItems(): ArrayList<Item> {
         val list = arrayListOf<Item>()
        for (i in 0..10) {
            val item = Item()
            item.text = "${i}行目"
            list.add(item)
        }
        return list
    }
}