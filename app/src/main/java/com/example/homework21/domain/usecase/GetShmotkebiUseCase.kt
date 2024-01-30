package com.example.homework21.domain.usecase


import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebisRepository
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShmotkebiUseCase @Inject constructor(
    private val shmotkebisRepository: ShmotkebisRepository
){
    operator fun invoke(): Flow<ResultWrapper<List<Shmotkebi>>> {
        return shmotkebisRepository.getShmotkebi()
    }
}