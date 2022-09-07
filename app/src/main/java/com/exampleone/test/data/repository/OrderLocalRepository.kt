package com.exampleone.test.data.repository

import androidx.lifecycle.LiveData
import com.exampleone.test.data.localDB.OrderLocalDao
import com.exampleone.test.data.models.OrderLocalModel
import com.exampleone.test.domain.repository.OrderLocalCall

class OrderLocalRepository (private val orderLocalDao: OrderLocalDao
): OrderLocalCall {

    override suspend fun insert(orderLocalModel: OrderLocalModel) {
        orderLocalDao.insert(orderLocalModel)    }

    override fun loadOrder(): LiveData<List<OrderLocalModel>> {
        return orderLocalDao.loadOrder()    }

    override suspend fun clear() {
        orderLocalDao.clear()    }



}