package com.exampleone.test.presentation.Tabs.Account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exampleone.test.R
import com.exampleone.test.databinding.AccountBinding
import com.exampleone.test.presentation.viewModels.OrderLocalViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class Account : Fragment() {

    private var _binding: AccountBinding? = null
    private val binding: AccountBinding
        get() = _binding ?: throw RuntimeException("FragmentAccount is null")

    private var orderAdapter: OrderAdapter? = null
    private val orderLocalViewModel: OrderLocalViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.account, container, false)

        initRecyclerOrder()
        loadOrders()

        binding.clearOrders.setOnClickListener(View.OnClickListener {
            orderLocalViewModel.clearOrders()
        })

        return binding.root
    }

    private fun initRecyclerOrder() {
        binding.listOrders.layoutManager =
            LinearLayoutManager(context)
        orderAdapter = OrderAdapter()
        binding.listOrders.adapter = orderAdapter

    }

    private fun loadOrders() {

        orderLocalViewModel.loadOrder.observe(viewLifecycleOwner, Observer {
            orderAdapter?.setList(it)
            orderAdapter?.notifyDataSetChanged()

        })


    }


}