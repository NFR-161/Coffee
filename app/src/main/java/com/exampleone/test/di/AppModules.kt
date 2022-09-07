package com.exampleone.test.di

import androidx.room.Room
import com.exampleone.test.data.dataSource.CoffeeApiDataSource
import com.exampleone.test.data.dataSource.CoffeeDataSource
import com.exampleone.test.data.dataSourceIMPL.CoffeeApiDataSourceIMPL
import com.exampleone.test.data.dataSourceIMPL.CoffeeDataSourceIMPL
import com.exampleone.test.data.localDB.DataBaseCoffee
import com.exampleone.test.data.repository.CardRepository
import com.exampleone.test.data.repository.CoffeeRepository
import com.exampleone.test.data.repository.OrderApiRepository
import com.exampleone.test.data.repository.OrderLocalRepository
import com.exampleone.test.domain.repository.CardCall
import com.exampleone.test.domain.repository.CoffeeCall
import com.exampleone.test.domain.repository.OrderApiCall
import com.exampleone.test.domain.repository.OrderLocalCall
import com.exampleone.test.domain.useCase.CardUseCase
import com.exampleone.test.domain.useCase.CoffeeUseCase
import com.exampleone.test.domain.useCase.OrderApiUseCase
import com.exampleone.test.domain.useCase.OrderLocalUseCase
import com.exampleone.test.presentation.viewModels.CardViewModel
import com.exampleone.test.presentation.viewModels.CoffeeViewModel
import com.exampleone.test.presentation.viewModels.OrderApiViewModel
import com.exampleone.test.presentation.viewModels.OrderLocalViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val coffee = module{

    single {
        Room.databaseBuilder(androidContext(), DataBaseCoffee::class.java,
            "dbC").build()
    }

    single { get<DataBaseCoffee>().coffeeDao }


    single<CoffeeDataSource> {
        CoffeeDataSourceIMPL(
            get()
        )
    }

    single<CoffeeApiDataSource> {
        CoffeeApiDataSourceIMPL(
            get()
        )
    }

    single<CoffeeCall> { CoffeeRepository(get(),get()) }

    single { CoffeeUseCase(get()) }

    viewModel { CoffeeViewModel(get()) }

}

val card = module{

    single {
        Room.databaseBuilder(androidContext(), DataBaseCoffee::class.java,
            "dbO").build()
    }

    single { get<DataBaseCoffee>().cardDao }


    single<CardCall> { CardRepository(get()) }

    single { CardUseCase(get()) }

    viewModel { CardViewModel(get()) }

}

val order = module{

    single {
        Room.databaseBuilder(androidContext(), DataBaseCoffee::class.java,
            "dbS").build()
    }

    single { get<DataBaseCoffee>().orderLocalDao }


    single<OrderLocalCall> { OrderLocalRepository(get()) }

    single { OrderLocalUseCase(get()) }

    viewModel { OrderLocalViewModel(get()) }

}

val orderApi= module{

    single<OrderApiCall> { OrderApiRepository() }

    single { OrderApiUseCase(get()) }

    viewModel { OrderApiViewModel(get()) }

}


