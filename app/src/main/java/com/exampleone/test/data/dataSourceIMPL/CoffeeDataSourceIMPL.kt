package com.exampleone.test.data.dataSourceIMPL

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exampleone.test.data.dataSource.CoffeeDataSource
import com.exampleone.test.data.localDB.CoffeeDao
import com.exampleone.test.data.mappers.CoffeeMapper
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.domain.info.CoffeeInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeDataSourceIMPL(private val dao: CoffeeDao) :
    CoffeeDataSource {

    private val mapper = CoffeeMapper()


    override fun insert(coffeeInfo: CoffeeInfo) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(mapper.mapCoffeeInfoToCoffeeModel(coffeeInfo))
        }
    }

    override fun loadCoffee(): LiveData<List<CoffeeInfo>> {

        return Transformations.map(dao.loadCoffee()) {
            it.map {
                mapper.mapCoffeeModelToCoffeeInfo(it)
            }
        }

    }


    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()
        }
    }


}