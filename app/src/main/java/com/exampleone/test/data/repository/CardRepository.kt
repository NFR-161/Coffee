package com.exampleone.test.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exampleone.test.data.localDB.CardDao
import com.exampleone.test.data.mappers.CardMapper
import com.exampleone.test.domain.info.CardInfo
import com.exampleone.test.domain.repository.CardCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardRepository(private val dao: CardDao) : CardCall {
    private val mapper = CardMapper()
    //val products = dao.loadCoffee()

    override suspend fun insert(cardInfo: CardInfo) {
        dao.insert(mapper.mapCardInfoToCardModel(cardInfo))
    }

    override suspend fun updateProductToCard(cardInfo: CardInfo) {
        dao.updateProductToCard(mapper.mapCardInfoToCardModel(cardInfo))
    }

    override fun loadCoffeeFromCard(): LiveData<List<CardInfo>> {
        return Transformations.map(dao.loadCoffeeFromCard()) {
            it.map {
                mapper.mapCardModelToCardInfo(it)
            }
        }
    }

    override fun loadCoffeeToCardFromCardProduct(idProduct: String): LiveData<List<CardInfo>> {
        return Transformations.map(dao.loadCoffeeToCardFromCardProduct(idProduct)) {
            it.map {
                mapper.mapCardModelToCardInfo(it)
            }
        }
    }

    override suspend fun deleteProductFromCard(idProduct: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProductFromCard(idProduct)
        }
    }

    override suspend fun  deleteProductInCardFromCardProduct(idProduct: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao. deleteProductInCardFromCardProduct(idProduct)
        }
    }

    override suspend fun clear() {
        dao.clear()
    }


}
