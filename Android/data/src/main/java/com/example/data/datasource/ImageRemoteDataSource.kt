package com.example.data.datasource

import com.example.data.api.ImageApi
import com.example.data.response.ImageGroupsResponse
import javax.inject.Inject

class ImageRemoteDataSource @Inject constructor(
    private val imageApi: ImageApi
) {
    suspend fun getImageGroups(): ImageGroupsResponse {
        return  imageApi.getImageGroups()
    }

}