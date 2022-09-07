package com.exampleone.test.data.mappers

import com.exampleone.test.data.models.CardModel
import com.exampleone.test.domain.info.CardInfo

class CardMapper {

    fun mapCardInfoToCardModel(cardInfo: CardInfo) = CardModel(
        id = cardInfo.id,
        name = cardInfo.name,
        image = cardInfo.image,
        price = cardInfo.price,
        idProduct = cardInfo.idProduct,
        count = cardInfo.count,
        totalPrice = cardInfo.totalPrice

    )

    fun mapCardModelToCardInfo(cardModel: CardModel) = CardInfo(
        id = cardModel.id,
        name = cardModel.name,
        image = cardModel.image,
        price = cardModel.price,
        idProduct = cardModel.idProduct,
        count = cardModel.count,
        totalPrice = cardModel.totalPrice
    )


}