package com.example.homework21.di


import android.content.Context
import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.remote.service.ShmotkebisService
import com.example.homework21.data.repository.NetworkRepositoryImpl
import com.example.homework21.data.repository.ShmotkebisRepositoryImpl
import com.example.homework21.domain.repository.ShmotkebisRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideShmotkebisNetworkRepository(
        shmotkebisService: ShmotkebisService,
        @ApplicationContext context: Context
    ): NetworkRepositoryImpl {
        return NetworkRepositoryImpl(
            shmotkebisService = shmotkebisService,
            context = context
        )
    }

    @Singleton
    @Provides
    fun provideShmotkebisRepository(
        shmotkebisDao: ShmotkebisDao,
        networkRepositoryImpl:NetworkRepositoryImpl
    ): ShmotkebisRepository {
        return ShmotkebisRepositoryImpl(
            shmotkebisDao = shmotkebisDao,
            networkRepository = networkRepositoryImpl
        )
    }

}