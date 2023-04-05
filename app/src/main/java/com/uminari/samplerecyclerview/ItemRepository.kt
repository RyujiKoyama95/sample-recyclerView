package com.uminari.samplerecyclerview

class ItemRepository(private val itemDao: ItemDao) {
    suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }
    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }
    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }
    suspend fun getAllItems(): List<Item> {
        itemDao.getAllItems()
    }
}