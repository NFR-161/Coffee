package com.exampleone.test.domain.repository


import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.domain.info.CoffeeInfo

interface CoffeeCall {
    fun loadCoffee(): LiveData<List<CoffeeInfo>>
    suspend fun startMigration(context: Context,progressBar_ID: ProgressBar)
}
