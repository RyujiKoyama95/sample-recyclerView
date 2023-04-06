package com.uminari.samplerecyclerview

import androidx.room.*

@Dao
interface ItemDao {
    @Insert
    suspend fun addItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): List<Item>
}