package com.exampleone.test.domain.repository

import androidx.lifecycle.LiveData
import com.exampleone.test.domain.info.CardInfo

interface CardCall {

    suspend fun insert(cardInfo: CardInfo)

    suspend fun updateProductToCard(cardInfo: CardInfo)

    fun loadCoffeeFromCard(): LiveData<List<CardInfo>>

    fun loadCoffeeToCardFromCardProduct(idProduct:String): LiveData<List<CardInfo>>

    suspend fun deleteProductFromCard(idProduct:Int)

    suspend fun  deleteProductInCardFromCardProduct(idProduct:String)

    suspend fun clear()

}