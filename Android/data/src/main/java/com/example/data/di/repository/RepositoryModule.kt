package com.example.data.di.repository

import com.example.data.repository.MQTTRepositoryImpl
import com.example.domain.repository.MQTTRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMQTTRepository(

    ): MQTTRepository {
        return MQTTRepositoryImpl()
    }

}