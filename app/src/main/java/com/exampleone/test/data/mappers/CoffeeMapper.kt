package com.exampleone.test.data.mappers

import com.exampleone.test.data.models.CardModel
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.domain.info.CardInfo
import com.exampleone.test.domain.info.CoffeeInfo

class CoffeeMapper {

    fun mapCoffeeInfoToCoffeeModel(coffeeInfo: CoffeeInfo) = CoffeeModel(
        id = coffeeInfo.id,
        name = coffeeInfo.name,
        image = coffeeInfo.image,
        description= coffeeInfo.description,
        price = coffeeInfo.price



    )

    fun mapCoffeeModelToCoffeeInfo(coffeeModel: CoffeeModel) = CoffeeInfo(
        id = coffeeModel.id,
        name = coffeeModel.name,
        image = coffeeModel.image,
        description= coffeeModel.description,
        price = coffeeModel.price
    )
}