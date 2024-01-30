package com.example.homework21.data.repository


import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.remote.handlers.HandleResponse
import com.example.homework21.data.remote.service.ShmotkebisService
import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebisRepository
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShmotkebisRepositoryImpl @Inject constructor(
    private val shmotkebisDao: ShmotkebisDao,
    private val shmotkebisService: ShmotkebisService,
    private val handleResponse: HandleResponse
):ShmotkebisRepository {
    override fun getShmotkebi(): Flow<ResultWrapper<List<Shmotkebi>>> {
        TODO()
    }


}