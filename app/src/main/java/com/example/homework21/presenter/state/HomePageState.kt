package com.example.homework21.presenter.state

import com.example.homework21.presenter.model.ShmotkebisUI

data class HomePageState(
    val isLoading:Boolean = false,
    val isSuccess: List<ShmotkebisUI>? = null,
    val errorMessage:String? = null,
) {
}