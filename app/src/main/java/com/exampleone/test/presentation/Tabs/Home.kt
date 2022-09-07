package com.exampleone.test.presentation.Tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.exampleone.test.R
import com.exampleone.test.databinding.HomeBinding
import com.exampleone.test.presentation.ProposalAdapter
import com.google.android.material.tabs.TabLayoutMediator

class Home : Fragment() {

    private var _binding: HomeBinding? = null
    private val binding: HomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHome is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = DataBindingUtil.inflate(inflater, R.layout.home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.slider.adapter = ProposalAdapter(context as FragmentActivity)

        val tabLayoutMediator = TabLayoutMediator(
            binding.tabSlider,
            binding.slider
        ) { _, _ -> }
        tabLayoutMediator.attach()
    }

}