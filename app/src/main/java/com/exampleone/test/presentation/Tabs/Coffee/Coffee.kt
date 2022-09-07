package com.exampleone.test.presentation.Tabs.Coffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampleone.test.R
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.databinding.CoffeeBinding
import com.exampleone.test.domain.info.CoffeeInfo
import com.exampleone.test.presentation.viewModels.CardViewModel
import com.exampleone.test.presentation.viewModels.CoffeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Coffee : Fragment() {

    private var _binding: CoffeeBinding? = null
    private val binding: CoffeeBinding
        get() = _binding ?: throw RuntimeException("FragmentCoffee is null")

    private var coffeeAdapter: CoffeeAdapter? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.coffee, container, false)

        initRecyclerCoffee()
        loadCoffee()

        return binding.root
    }

    private fun initRecyclerCoffee() {
        binding.apply {
            catalogCoffee.layoutManager =
                LinearLayoutManager(context)
            coffeeAdapter =
                CoffeeAdapter({ coffeeInfo: CoffeeInfo ->
                    addToCard(
                        coffeeInfo
                    )
                }, { coffeeInfo ->
                    removeFromCard(
                        coffeeInfo
                    )
                }, { idProduct, addToBasket,
                     removeFromBasket ->
                    loadCoffeeToCardFromCardProduct(
                        idProduct, addToBasket, removeFromBasket
                    )
                })
            catalogCoffee.adapter = coffeeAdapter

        }
    }

    private fun loadCoffee() {

        coffeeViewModel.loadCoffee.observe(viewLifecycleOwner, Observer {
            coffeeAdapter?.setList(it)
            coffeeAdapter?.notifyDataSetChanged()
        })
    }

    private fun addToCard(coffeeInfo: CoffeeInfo) {
        cardViewModel.startInsert(
            coffeeInfo.name, coffeeInfo.image, coffeeInfo.price, coffeeInfo.id.toString(),
            "1"
        )
    }

    private fun removeFromCard(coffeeInfo: CoffeeInfo) {
        cardViewModel. deleteProductInCardFromCardProduct(coffeeInfo.id.toString())
    }

    private fun loadCoffeeToCardFromCardProduct(
        idProduct: Int, addToBasket: AppCompatImageButton,
        removeFromBasket: AppCompatImageButton
    ) {

        cardViewModel.loadCoffeeToCardFromCardProduct(idProduct.toString())
            .observe(viewLifecycleOwner, Observer {

                val count = it.count()

                if (count > 0) {
                    addToBasket.visibility = View.GONE
                    removeFromBasket.visibility = View.VISIBLE
                } else {
                    addToBasket.visibility = View.VISIBLE
                    removeFromBasket.visibility = View.GONE
                }
            })

    }

}