package com.exampleone.test.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exampleone.test.data.models.CoffeeModel

@Dao
interface CoffeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coffeeModel: CoffeeModel)

    @Query("SELECT * FROM coffee_data_table")
    fun loadCoffee(): LiveData<List<CoffeeModel>>

    @Query("DELETE FROM coffee_data_table")
    suspend fun clear()
}