package com.exampleone.test.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee_data_table")
class CoffeeModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "coffee_id")
    val id:Int,

    @ColumnInfo(name = "coffee_name")
    val name:String,

    @ColumnInfo(name = "coffee_image")
    val image:String,

    @ColumnInfo(name = "coffee_description")
    val description:String,

    @ColumnInfo(name = "coffee_price")
    val price:String
)
