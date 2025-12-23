package com.example.data.di.datasource

import com.example.data.api.ImageApi
import com.example.data.datasource.ImageRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideItemRemoteDataSource(
        imageApi: ImageApi
    ): ImageRemoteDataSource {
        return ImageRemoteDataSource(imageApi)
    }
}