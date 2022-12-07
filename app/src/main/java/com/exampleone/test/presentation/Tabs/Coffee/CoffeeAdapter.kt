package com.exampleone.test.presentation.Tabs.Coffee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exampleone.test.R
import com.exampleone.test.data.models.CoffeeModel
import com.exampleone.test.databinding.CoffeeItemBinding
import com.exampleone.test.domain.info.CoffeeInfo
import com.squareup.picasso.Picasso

class CoffeeAdapter(
    private val addToCard: (CoffeeInfo) -> Unit, private val removeFromCard: (CoffeeInfo) -> Unit,
    private val loadCoffeeToCardFromCardProduct: (Int, AppCompatImageButton, AppCompatImageButton) -> Unit
) :
    RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder>() {

    private val coffee = ArrayList<CoffeeInfo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CoffeeItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.coffee_item, parent, false)
        return CoffeeHolder(binding)
    }

    override fun getItemCount(): Int {
        return coffee.size

    }

    override fun onBindViewHolder(holder: CoffeeHolder, position: Int) {
        holder.bind(coffee[position], addToCard, removeFromCard, loadCoffeeToCardFromCardProduct)

    }

    fun setList(coffeeList: List<CoffeeInfo>) {
        coffee.clear()
        coffee.addAll(coffeeList)

    }

    class CoffeeHolder(val binding: CoffeeItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(
            coffeeInfo: CoffeeInfo, addToCard: (CoffeeInfo) -> Unit,
            removeFromCard: (CoffeeInfo) -> Unit,
            loadCoffeeToCardFromCardProduct: (Int, AppCompatImageButton, AppCompatImageButton) -> Unit
        ) = with(binding) {

            val getImage = coffeeInfo.image
            Picasso.get().load(getImage).into(imageCoffee)
            nameCoffee.text = coffeeInfo.name
            descriptionCoffee.text = coffeeInfo.description
            priceCoffee.text = coffeeInfo.price

            binding.addToCard.setOnClickListener(View.OnClickListener {
                addToCard(coffeeInfo)

            })

            binding.removeFromCard.setOnClickListener(View.OnClickListener {
                removeFromCard(coffeeInfo)

            })

            loadCoffeeToCardFromCardProduct(
                coffeeInfo.id,
                binding.addToCard,
                binding.removeFromCard
            )

        }

    }

}