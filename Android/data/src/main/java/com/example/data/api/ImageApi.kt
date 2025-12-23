package com.example.data.api

import com.example.data.constant.ServerConstant
import com.example.data.response.ImageGroupsResponse
import retrofit2.http.GET

interface ImageApi {

    @GET("${ServerConstant.Image.Gruoped}/?limit=500")
    suspend fun getImageGroups(): ImageGroupsResponse
}