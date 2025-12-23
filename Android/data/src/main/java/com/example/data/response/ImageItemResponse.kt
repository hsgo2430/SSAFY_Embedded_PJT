package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ImageItemResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("created_at")
    val created_at: String,

    @SerializedName("image_url")
    val image_url: String,

    @SerializedName("view_url")
    val view_url: String,

    @SerializedName("download_url")
    val download_url: String,

    @SerializedName("filename")
    val filename: String,

    @SerializedName("size_bytes")
    val size_bytes: Int,

    @SerializedName("latitude")
    val latitude: String?,

    @SerializedName("longitude")
    val longitude: String?
)