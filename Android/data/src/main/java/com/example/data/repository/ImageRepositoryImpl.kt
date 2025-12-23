package com.example.data.repository

import android.util.Log
import com.example.data.datasource.ImageRemoteDataSource
import com.example.data.dto.toImageGroups
import com.example.domain.model.ImageGroups
import com.example.domain.repository.ImageRepository
import retrofit2.HttpException
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDataSource: ImageRemoteDataSource
) : ImageRepository {

    override suspend fun getImageGroups(): Result<ImageGroups> {
        return try {
            val groups = imageRemoteDataSource.getImageGroups().toImageGroups()
            Log.d("로그", groups.toString())
            Result.success(groups)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: kotlinx.coroutines.CancellationException) {
            throw e
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}