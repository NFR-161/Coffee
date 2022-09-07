package com.exampleone.test.presentation.Tabs.Card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exampleone.test.R
import com.exampleone.test.databinding.CardItemBinding
import com.exampleone.test.domain.info.CardInfo
import com.squareup.picasso.Picasso

class CardAdapter(
    private val deleteFromCard: (CardInfo) -> Unit, private val lessCount: (CardInfo) -> Unit,
    private val moreCount: (CardInfo) -> Unit
) :
    RecyclerView.Adapter<CardAdapter.CardHolder>() {

    private val productsFromCard = ArrayList<CardInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsFromCard.size
    }


    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(
            productsFromCard[position], deleteFromCard,
            moreCount, lessCount
        )

    }

    fun setList(cardList: List<CardInfo>) {
        productsFromCard.clear()
        productsFromCard.addAll(cardList)

    }


    class CardHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            cardInfo: CardInfo, deleteFromCard: (CardInfo) -> Unit,
            moreCount: (CardInfo) -> Unit, lessCount: (CardInfo) -> Unit
        ) {

            val getImage = cardInfo.image
            Picasso.get().load(getImage).into(binding.imageProductCard)
            binding.nameProductCard.text = cardInfo.name
            binding.countProductBasket.text = cardInfo.count
            binding.priceProductCard.text = cardInfo.price
            binding.totalPriceProductCard.text = cardInfo.totalPrice

            binding.removeFromCardProductCard.setOnClickListener(View.OnClickListener {
                deleteFromCard(cardInfo)
            })

            binding.moreProductBasket.setOnClickListener(View.OnClickListener {
                moreCount(cardInfo)

            })

            binding.lessProductBasket.setOnClickListener(View.OnClickListener {
                lessCount(cardInfo)

            })


        }


    }

}