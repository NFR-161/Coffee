package com.exampleone.test.domain.useCase

import androidx.lifecycle.LiveData
import com.exampleone.test.data.models.OrderLocalModel
import com.exampleone.test.domain.repository.OrderLocalCall

class OrderLocalUseCase (private val orderLocalCall: OrderLocalCall) {


    suspend fun insert(orderLocalModel: OrderLocalModel) {
        orderLocalCall.insert(orderLocalModel)    }

    fun loadOrder(): LiveData<List<OrderLocalModel>> {
        return orderLocalCall.loadOrder()    }

    suspend fun clear() {
        orderLocalCall.clear()    }

}