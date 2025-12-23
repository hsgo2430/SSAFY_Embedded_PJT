package com.example.domain.usecase.image

import com.example.domain.model.ImageGroups
import com.example.domain.repository.ImageRepository
import com.example.domain.repository.MQTTRepository
import javax.inject.Inject

class GetImageGroupsUseCase @Inject constructor(
    private val imageRepository: ImageRepository
){
    suspend operator fun invoke(

    ): Result<ImageGroups> {
        return imageRepository.getImageGroups()
    }

}