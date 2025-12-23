package com.example.domain.model

data class ImageItem(
    val id: Int,
    val created_at: String,
    val image_url: String,
    val view_url: String,
    val download_url: String,
    val filename: String,
    val size_bytes: Int,
    val latitude: String,
    val longitude: String
)