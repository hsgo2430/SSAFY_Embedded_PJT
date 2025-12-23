package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ImageGroupResponse (

    @SerializedName("date")
    val date: String,

    @SerializedName("items")
    val items: List<ImageItemResponse>
)