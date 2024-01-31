package com.example.homework21.presenter.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework21.R
import com.example.homework21.databinding.FragmentHomePageBinding
import com.example.homework21.presenter.base.BaseFragment
import com.example.homework21.presenter.event.HomePageEvent
import com.example.homework21.presenter.extension.showSnackBar
import com.example.homework21.presenter.state.HomePageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    private val viewModel:HomePageViewModel by viewModels()
    private lateinit var myAdapter: ShmotkiRecyclerAdapter

    override fun bind() {
        viewModel.onEvent(HomePageEvent.LoadUser)
        myAdapter = ShmotkiRecyclerAdapter()
        binding.apply {
            shmotkebisRecycler.adapter = myAdapter
        }
        myAdapter.submitList(viewModel.uiStateFlow.value.isSuccess?: emptyList())
    }

    override fun bindObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiStateFlow.collect{
                    handleResult(it)
                }
            }
        }

    }

    private fun handleResult(state:HomePageState){
        state.errorMessage?.let {
            showErrorMessage(it)
            viewModel.onEvent(HomePageEvent.ResetErrorMessageToNull)
        }

        showLoader(state.isLoading)

        state.isSuccess?.let {
            myAdapter.submitList(it)
            if(it.isEmpty()){
                showErrorMessage(getString(R.string.not_found_items))
            }
        }
    }

    private fun showErrorMessage(errorMessage:String){
        binding.root.showSnackBar(errorMessage)
    }

    private fun showLoader(loading:Boolean){
        binding.progressBar.visibility = if(loading) View.VISIBLE else View.GONE
    }

}