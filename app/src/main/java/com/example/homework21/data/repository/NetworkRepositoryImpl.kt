package com.example.homework21.data.repository

import android.content.Context
import com.example.homework21.data.local.model.ShmotkebisEntity
import com.example.homework21.data.remote.is_online_checker.isOnline
import com.example.homework21.data.remote.mapper.toLocal
import com.example.homework21.data.remote.service.ShmotkebisService
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val shmotkebisService: ShmotkebisService,
    private val context: Context
) {
    fun getShmotkebi(): Flow<ResultWrapper<List<ShmotkebisEntity>>> = flow {
        if (isOnline(context)) {
            try {
                val response = shmotkebisService.getShmotkebi()
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    val bodyMappedToLocal = withContext(Dispatchers.Default){
                        body.map { it.toLocal() }
                    }
                    emit(ResultWrapper.Success(bodyMappedToLocal))
                } else {
                    emit(ResultWrapper.Error(response.errorBody()?.toString() ?: "Unknown Error"))
                }
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e.toString()))
            }
        } else {
            emit(ResultWrapper.Error("No Internet Connection"))
        }
    }
}