package com.example.domain.repository

import com.example.domain.model.ImageGroups

interface ImageRepository {
    suspend fun getImageGroups(): Result<ImageGroups>
}