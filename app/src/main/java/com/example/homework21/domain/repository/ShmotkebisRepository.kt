package com.example.homework21.domain.repository


import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow


interface ShmotkebisRepository {
    fun getShmotkebi(): Flow<ResultWrapper<List<Shmotkebi>>>
}