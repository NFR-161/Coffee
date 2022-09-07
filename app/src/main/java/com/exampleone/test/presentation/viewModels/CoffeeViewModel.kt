package com.exampleone.test.presentation.viewModels

import android.content.Context
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampleone.test.domain.useCase.CoffeeUseCase
import kotlinx.coroutines.launch

class CoffeeViewModel (private val coffeeUseCase: CoffeeUseCase): ViewModel() {

    val loadCoffee = coffeeUseCase.loadCoffee()


    fun migration(context: Context, progressBar: ProgressBar) = viewModelScope.launch {
        coffeeUseCase.startMigration(context,progressBar)

    }

}