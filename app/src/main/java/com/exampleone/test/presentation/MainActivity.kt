package com.exampleone.test.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.exampleone.test.R
import com.exampleone.test.databinding.ActivityMainBinding
import com.exampleone.test.presentation.Tabs.Account.Account
import com.exampleone.test.presentation.Tabs.Card.Card
import com.exampleone.test.presentation.Tabs.Coffee.Coffee
import com.exampleone.test.presentation.Tabs.Home
import com.exampleone.test.presentation.viewModels.CoffeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // binding?.bottomMainMenu?.selectedItemId = R.id.homeBottomMainMenu
        supportFragmentManager.beginTransaction().replace(R.id.mainContent, Home()).commit()

        binding?.progressBarID?.let { coffeeViewModel.migration(this, it) }

        setSupportActionBar(binding?.topMainMenu)

        binding?.bottomMainMenu?.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.homeBottomMainMenu -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, Home()).commit()
                R.id.coffeeBottomMainMenu -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, Coffee()).commit()
                R.id.cardBottomMainMenu -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, Card()).commit()
                R.id.accountBottomMainMenu -> supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContent, Account()).commit()
            }

            return@setOnItemSelectedListener true

        }

    }

}