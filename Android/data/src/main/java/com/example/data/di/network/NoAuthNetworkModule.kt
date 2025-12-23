package com.example.data.di.network

import com.example.data.annotation.NoAuth
import com.example.data.annotation.ServerRetrofit
import com.example.data.annotation.ServerUrl
import com.example.data.api.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoAuthNetworkModule {

    @NoAuth
    @Provides
    @Singleton
    fun provideAuthOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @ServerRetrofit
    @Provides
    @Singleton
    fun provideServerRetrofit(
        @ServerUrl baseUrl: String,
        @NoAuth okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /* Auth retrofit instances below */
    @Provides
    @Singleton
    fun provideImageApi(
        @ServerRetrofit retrofit: Retrofit
    ): ImageApi {
        return retrofit.create(ImageApi::class.java)
    }
//
//    @Provides
//    @Singleton
//    fun provideItemImageApi(
//        @XivApiRetrofit retrofit: Retrofit
//    ): ItemImageApi {
//        return retrofit.create(ItemImageApi::class.java)
//    }
}
