package com.exampleone.test.data.dataSource

import androidx.lifecycle.LiveData
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.domain.info.CoffeeInfo

interface CoffeeDataSource {
    fun insert(coffeeInfo: CoffeeInfo)
    fun loadCoffee(): LiveData<List<CoffeeInfo>>
    suspend fun clear()
}