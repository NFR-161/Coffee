package com.exampleone.test.presentation.Tabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.exampleone.test.R
import com.exampleone.test.databinding.CheckoutBinding
import com.exampleone.test.presentation.Tabs.Account.Account
import com.exampleone.test.presentation.viewModels.CardViewModel
import com.exampleone.test.presentation.viewModels.OrderApiViewModel
import com.exampleone.test.presentation.viewModels.OrderLocalViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class Checkout : BottomSheetDialogFragment() {

    private var _binding: CheckoutBinding? = null

    private val binding: CheckoutBinding
        get() = _binding ?: throw RuntimeException("Checkout is null")

    private val cardViewModel: CardViewModel by viewModel()
    private val orderLocalViewModel: OrderLocalViewModel by viewModel()
    private val orderApiViewModel: OrderApiViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.checkout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitCheckout.setOnClickListener(View.OnClickListener {

            cardViewModel.loadCoffeeFromCard.observe(viewLifecycleOwner, Observer {

                val totalOrder: Int = it.sumOf { it.totalPrice.toInt() }

                val descriptionOrder =
                    it.joinToString("")
                    { it.name + ": count - " + it.count + ", price - " + it.totalPrice + " $; " }

                orderLocalViewModel.startInsert(
                    binding.enterNameCheckout.text.toString(),
                    binding.enterPhoneCheckout.text.toString(), descriptionOrder,
                    totalOrder.toString()
                )

                orderApiViewModel.insert(
                    (context as FragmentActivity), binding.enterNameCheckout.text.toString(),
                    binding.enterPhoneCheckout.text.toString(), descriptionOrder,
                    totalOrder.toString()
                )

                binding.enterNameCheckout.setText("")
                binding.enterPhoneCheckout.setText("")

                dismiss()

                cardViewModel.clearCard()

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.mainContent, Account())?.commit()
                val  bottomMain= activity?.findViewById<BottomNavigationView>(R.id.bottomMainMenu)
                if (bottomMain != null) {
                    bottomMain.selectedItemId = R.id.accountBottomMainMenu
                }

            })

        })

    }

}