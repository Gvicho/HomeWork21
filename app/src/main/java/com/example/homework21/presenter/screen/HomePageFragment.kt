package com.example.homework21.presenter.screen

import androidx.fragment.app.viewModels
import com.example.homework21.databinding.FragmentHomePageBinding
import com.example.homework21.presenter.base.BaseFragment


class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    private val viewModel:HomePageViewModel by viewModels()

    override fun bind() {

    }

}