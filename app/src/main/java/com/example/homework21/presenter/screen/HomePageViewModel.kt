package com.example.homework21.presenter.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework21.domain.usecase.GetShmotkebiUseCase
import com.example.homework21.domain.wrapper.ResultWrapper
import com.example.homework21.presenter.event.HomePageEvent
import com.example.homework21.presenter.mapper.toUI
import com.example.homework21.presenter.state.HomePageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getShmotkebiUseCase: GetShmotkebiUseCase
) :ViewModel() {

    private val _uiStateFlow = MutableStateFlow(HomePageState())
    val uiStateFlow : StateFlow<HomePageState> = _uiStateFlow

    fun onEvent(event: HomePageEvent){
        when(event){
            HomePageEvent.LoadUser -> loadShmotkebi()
            HomePageEvent.ResetErrorMessageToNull -> resetErrorMessageToNull()
        }
    }

    private fun loadShmotkebi(){
        viewModelScope.launch {
            getShmotkebiUseCase().collect{result ->
                when(result){
                    is ResultWrapper.Error -> {
                        _uiStateFlow.update {
                            it.copy(errorMessage = result.errorMessage, isSuccess = null)
                        }
                    }
                    is ResultWrapper.Loading -> {
                        _uiStateFlow.update {
                            it.copy(isLoading = result.loading)
                        }
                    }
                    is ResultWrapper.Success -> {
                        _uiStateFlow.update {state->
                            state.copy(
                                isSuccess = result.data!!.map {
                                    it.toUI()
                                }
                            )
                        }
                    }
                }
            }
        }

    }

    private fun resetErrorMessageToNull(){
        _uiStateFlow.update {
            it.copy(errorMessage = null)
        }
    }

}