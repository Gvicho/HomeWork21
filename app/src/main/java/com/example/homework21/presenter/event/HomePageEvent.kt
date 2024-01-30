package com.example.homework21.presenter.event

sealed class HomePageEvent {
    data object LoadUser:HomePageEvent()
    data object ResetErrorMessageToNull: HomePageEvent()
}