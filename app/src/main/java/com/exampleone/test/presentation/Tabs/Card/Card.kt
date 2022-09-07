package com.exampleone.test.presentation.Tabs.Card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampleone.test.R
import com.exampleone.test.databinding.CardBinding
import com.exampleone.test.domain.info.CardInfo
import com.exampleone.test.presentation.Tabs.Checkout
import com.exampleone.test.presentation.viewModels.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Card : Fragment(), View.OnClickListener {

    private var _binding: CardBinding? = null
    private val binding: CardBinding
        get() = _binding ?: throw RuntimeException("FragmentCard is null")

    private var cardAdapter: CardAdapter? = null
    private val cardViewModel: CardViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.card, container, false)
        initRecyclerCard()
        loadCoffeeFromCard()
        binding.clearCard.setOnClickListener(this)
        binding.checkoutCard.setOnClickListener(this)

        return binding?.root
    }

    private fun initRecyclerCard() {
        binding.listCard.layoutManager =
            LinearLayoutManager(context)
        cardAdapter =
            CardAdapter({ cardInfo: CardInfo ->
                deleteFromCard(
                    cardInfo
                )
            }, { cardInfo: CardInfo ->
                lessCount(
                    cardInfo
                )
            }, { cardInfo: CardInfo ->
                moreCount(
                    cardInfo
                )
            })
        binding.listCard.adapter = cardAdapter
    }

    private fun loadCoffeeFromCard() {
        cardViewModel.loadCoffeeFromCard.observe(viewLifecycleOwner, Observer {
            cardAdapter?.setList(it)
            cardAdapter?.notifyDataSetChanged()

            val total: Int = it.sumOf { it.totalPrice.toInt() }

            binding.totalOrder.text = total.toString()
        })

    }

    private fun deleteFromCard(cardInfo: CardInfo) {

        cardViewModel.deleteProductFromCard(cardInfo.id)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.clearCard -> cardViewModel.clearCard()

            R.id.checkoutCard -> {

                val checkout = Checkout()
                checkout.show(
                    (context as FragmentActivity)
                        .supportFragmentManager, "checkout"
                )

            }
        }
    }

    private fun lessCount(cardInfo: CardInfo) {

        var count: Int = cardInfo.count.toInt()
        count--

        if (count < 1) {
            cardViewModel.updateProductToCard(
                CardInfo(
                    cardInfo.id, cardInfo.name,
                    cardInfo.image, cardInfo.price, cardInfo.idProduct, "1",
                    (cardInfo.price.toInt() * 1).toString()
                )
            )

        } else {

            cardViewModel.updateProductToCard(
                CardInfo(
                    cardInfo.id, cardInfo.name,
                    cardInfo.image, cardInfo.price, cardInfo.idProduct, count.toString(),
                    (cardInfo.price.toInt() * count).toString()
                )
            )
        }

    }

    private fun moreCount(cardInfo: CardInfo) {
        var count: Int = cardInfo.count.toInt()
        count++

        cardViewModel.updateProductToCard(
            CardInfo(
                cardInfo.id, cardInfo.name,
                cardInfo.image, cardInfo.price, cardInfo.idProduct, count.toString(),
                (cardInfo.price.toInt() * count).toString()
            )
        )
    }
}