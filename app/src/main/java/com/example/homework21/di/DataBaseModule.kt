package com.example.homework21.di

import android.content.Context
import androidx.room.Room
import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.local.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "ShmotkebisDataBase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDataBase:AppDataBase):ShmotkebisDao{
        return appDataBase.shmotkebisDao()
    }
}