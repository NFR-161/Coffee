package com.exampleone.test.data.repository

import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import com.exampleone.test.data.dataSource.CoffeeApiDataSource
import com.exampleone.test.data.dataSource.CoffeeDataSource
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.domain.info.CoffeeInfo
import com.exampleone.test.domain.repository.CoffeeCall

class CoffeeRepository (private val coffeeApiDataSource: CoffeeApiDataSource,
                        private val coffeeDataSource: CoffeeDataSource
): CoffeeCall {

    //val products = dao.loadCoffee()

    override fun loadCoffee(): LiveData<List<CoffeeInfo>> {
        return coffeeDataSource.loadCoffee()

    }

    override suspend fun startMigration(context: Context,progressBar_ID: ProgressBar) {
        coffeeDataSource.clear()
        coffeeApiDataSource.startMigration(context,progressBar_ID)
    }

}
