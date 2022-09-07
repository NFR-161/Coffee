package com.exampleone.test.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampleone.test.domain.info.CardInfo
import com.exampleone.test.domain.useCase.CardUseCase
import kotlinx.coroutines.launch

class CardViewModel (private val cardUseCase: CardUseCase): ViewModel() {


    fun startInsert(
        nameProduct: String,
        imageProduct: String,
        priceProduct: String,
        idProduct: String,
        countProduct: String
    ) {
        insert(
            CardInfo(
                0,
                nameProduct,
                imageProduct,
                priceProduct,
                idProduct,
                countProduct,
                (priceProduct.toInt() * countProduct.toInt()).toString()
            )
        )
    }

    private fun insert(cardInfo: CardInfo) = viewModelScope.launch{

        cardUseCase.insert(cardInfo)
    }

    fun updateProductToCard(cardInfo: CardInfo) = viewModelScope.launch{

        cardUseCase.updateProductToCard(cardInfo)
    }

    val loadCoffeeFromCard = cardUseCase.loadCoffeeFromCard()

    fun loadCoffeeToCardFromCardProduct(idProduct:String): LiveData<List<CardInfo>> {
        return cardUseCase.loadCoffeeToCardFromCardProduct(idProduct)
    }

    fun deleteProductFromCard(idProduct:Int) = viewModelScope.launch{

        cardUseCase.deleteProductFromCard(idProduct)
    }

    fun  deleteProductInCardFromCardProduct(idProduct:String) = viewModelScope.launch{

        cardUseCase.deleteProductInCardFromCardProduct(idProduct)
    }

    fun clearCard() = viewModelScope.launch{

        cardUseCase.clear()
    }

}