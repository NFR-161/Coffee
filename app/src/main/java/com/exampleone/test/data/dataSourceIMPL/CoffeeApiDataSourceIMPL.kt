package com.exampleone.test.data.dataSourceIMPL

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.exampleone.test.data.api.ApiClient
import com.exampleone.test.data.dataSource.CoffeeApiDataSource
import com.exampleone.test.data.dataSource.CoffeeDataSource
import com.exampleone.test.data.models.CoffeeApiModel
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.domain.info.CoffeeInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoffeeApiDataSourceIMPL(private val coffeeDataSource: CoffeeDataSource) :
    CoffeeApiDataSource {

    override fun startMigration(context: Context, progressBar_ID: ProgressBar) {

        val call = ApiClient.instance?.api?.loadCoffeeApi()
        call?.enqueue(object : Callback<ArrayList<CoffeeApiModel>> {

            override fun onResponse(call: Call<ArrayList<CoffeeApiModel>>, response: Response<ArrayList<CoffeeApiModel>>) {
                var loadCoffee: ArrayList<CoffeeApiModel>? = null
                loadCoffee?.clear()
                loadCoffee = response.body()

                if (loadCoffee != null) {
                    for (audit in loadCoffee) {

                        audit.id?.let {
                            CoffeeInfo(
                                it,
                                audit.name.toString(),
                                audit.image.toString(),
                                audit.description.toString(),
                                audit.price.toString())
                        }?.let {
                            coffeeDataSource.insert(it)
                        }
                    }
                    showToast(context, "Загрузка")
                    progressBar_ID.visibility = View.GONE

                } else {
                    showToast(context, "Ошибка загрузки данных")
                    progressBar_ID.visibility = View.GONE
                }

            }

            override fun onFailure(call: Call<ArrayList<CoffeeApiModel>>, t: Throwable) {
                showToast(context, "ОШИБКА!НЕТ СЕТИ!")
            }
        })
    }

    private fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}