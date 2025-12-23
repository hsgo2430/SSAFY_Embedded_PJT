package com.example.data.dto

import com.example.data.response.ImageItemResponse
import com.example.domain.model.ImageItem

fun ImageItemResponse.toImageItem() = ImageItem(
    id = this.id,
    created_at = this.created_at,
    image_url = this.image_url,
    view_url = this.view_url,
    download_url = this.download_url,
    filename = this.filename,
    size_bytes = this.size_bytes,
    latitude = this.latitude ?: "",
    longitude = this.longitude?: ""
)