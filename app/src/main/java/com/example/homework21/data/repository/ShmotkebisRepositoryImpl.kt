package com.example.homework21.data.repository


import android.content.Context
import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.local.mapper.toDomain
import com.example.homework21.data.remote.is_online_checker.isOnline
import com.example.homework21.data.remote.mapper.toLocal
import com.example.homework21.data.remote.service.ShmotkebisService
import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebisRepository
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShmotkebisRepositoryImpl @Inject constructor(
    private val shmotkebisDao: ShmotkebisDao,
    private val shmotkebisService: ShmotkebisService,
    private val context: Context
):ShmotkebisRepository {
    override suspend fun getShmotkebi(): Flow<ResultWrapper<List<Shmotkebi>>> = flow {

        emit(ResultWrapper.Loading(true))

        try {
            if(isOnline(context)){
                val response = shmotkebisService.getShmotkebi()
                val body = response.body()
                if(response.isSuccessful && body !=null){
                    shmotkebisDao.insertAll(body.map { it.toLocal() })
                }else{
                    emit(ResultWrapper.Error(response.errorBody().toString()))
                }
            }
        }catch (e:Exception){
            emit(ResultWrapper.Error(e.toString()))
        }


        val shmotkebi = shmotkebisDao.getAllShmotkebi()
        emit(ResultWrapper.Success(data = shmotkebi.map { it.toDomain() }))

        emit(ResultWrapper.Loading(false))
    }

}