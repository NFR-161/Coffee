package com.exampleone.test.domain.repository

import androidx.lifecycle.LiveData
import com.exampleone.test.data.models.OrderLocalModel

interface OrderLocalCall {
    suspend fun insert(orderLocalModel: OrderLocalModel)
    fun loadOrder(): LiveData<List<OrderLocalModel>>
    suspend fun clear()
}