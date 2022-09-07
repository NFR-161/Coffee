package com.exampleone.test.domain.info

import androidx.room.ColumnInfo

data class CoffeeInfo(
    val id: Int,
    val name: String,
    val image: String,
    val description:String,
    val price:String
)

