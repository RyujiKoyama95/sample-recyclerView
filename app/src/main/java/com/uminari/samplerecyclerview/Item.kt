package com.uminari.samplerecyclerview

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey
    val id: Int,
    var title: String
)