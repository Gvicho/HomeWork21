package com.example.homework21.data.repository


import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.local.mapper.toDomain
import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebisRepository
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShmotkebisRepositoryImpl @Inject constructor(
    private val shmotkebisDao: ShmotkebisDao,
    private val networkRepository: NetworkRepositoryImpl
):ShmotkebisRepository {
    override suspend fun getShmotkebi(): Flow<ResultWrapper<List<Shmotkebi>>> = flow {

        emit(ResultWrapper.Loading(true))

        networkRepository.getShmotkebi().collect { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    val shmotkebiMappedToLocal = result.data
                    shmotkebiMappedToLocal?.let {
                        shmotkebisDao.insertAll(it)
                    }
                }
                is ResultWrapper.Error -> emit(ResultWrapper.Error(errorMessage = result.errorMessage?:""))
                is ResultWrapper.Loading -> {} // bno need because, here loading dosn't start or finish
            }
        }

        val shmotkebi = shmotkebisDao.getAllShmotkebi()
        val shmotkebiMappedToDomain = withContext(Dispatchers.Default){
            shmotkebi.map { it.toDomain() }
        }
        emit(ResultWrapper.Success(data = shmotkebiMappedToDomain))

        emit(ResultWrapper.Loading(false))
    }

}