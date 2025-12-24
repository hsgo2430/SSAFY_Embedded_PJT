package com.example.data.di.network

import com.example.data.annotation.NoAuth
import com.example.data.annotation.ServerRetrofit
import com.example.data.annotation.ServerUrl
import com.example.data.api.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoAuthNetworkModule {

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ServerHmacInterceptor =
        ServerHmacInterceptor("6NoqFBP8Y5n6AwMzQptUg6JguyOECA+t7iTCzE9GXXE=", hmacSecretB64 = "JvM2Pv0NyWGVxbNXI3eMz8l8N3sIwCkNGYFBZSu75Ik=")

    @NoAuth
    @Provides
    @Singleton
    fun provideAuthOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ServerHmacInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            addInterceptor(apiKeyInterceptor)
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
