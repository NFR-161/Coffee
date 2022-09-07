package com.exampleone.test.domain.useCase

import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.domain.info.CoffeeInfo
import com.exampleone.test.domain.repository.CoffeeCall

class CoffeeUseCase (private val coffeeCall: CoffeeCall) {

    fun loadCoffee(): LiveData<List<CoffeeInfo>> {

        return coffeeCall.loadCoffee()
    }
    suspend fun startMigration (context: Context,progressBar_ID: ProgressBar) {

        coffeeCall.startMigration(context,progressBar_ID)

    }

}