package com.example.data.di.network

import com.example.data.annotation.ServerUrl
import com.example.data.constant.ServerConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(

    ) = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY

    }

    @ServerUrl
    @Provides
    @Singleton
    fun provideServerUrl(
    ): String {
        return ServerConstant.BASE_URL
    }
}