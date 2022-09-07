package com.exampleone.test.domain.useCase

import androidx.lifecycle.LiveData
import com.exampleone.test.domain.info.CardInfo
import com.exampleone.test.domain.repository.CardCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardUseCase (private val cardCall: CardCall) {


    suspend fun insert(cardInfo: CardInfo) {
        cardCall.insert(cardInfo)    }

    suspend fun updateProductToCard(cardInfo: CardInfo) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.updateProductToCard(cardInfo)}
    }

    fun loadCoffeeFromCard(): LiveData<List<CardInfo>> {
        return cardCall.loadCoffeeFromCard()    }

    fun loadCoffeeToCardFromCardProduct(idProduct:String): LiveData<List<CardInfo>> {
        return cardCall.loadCoffeeToCardFromCardProduct(idProduct)    }

    suspend fun deleteProductFromCard(idProduct:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.deleteProductFromCard(idProduct)}
    }

    suspend fun  deleteProductInCardFromCardProduct(idProduct:String) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.deleteProductInCardFromCardProduct(idProduct)}
    }

    suspend fun clear() {
        cardCall.clear()    }


}