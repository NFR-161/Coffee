package com.exampleone.test.data.repository

import android.content.Context
import com.exampleone.test.data.api.ApiClient
import com.exampleone.test.domain.repository.OrderApiCall
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderApiRepository: OrderApiCall {

    override fun insert(context:Context, name:String, phone:String, description:String, priceOrder:String) {

        val callInsertCategory: Call<ResponseBody>? = ApiClient.instance?.api?.insert(name, phone, description, priceOrder)
        callInsertCategory?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                insert(context, name, phone, description, priceOrder)
            }
        })


    }
}