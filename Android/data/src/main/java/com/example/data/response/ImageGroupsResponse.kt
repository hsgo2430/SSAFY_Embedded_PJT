package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ImageGroupsResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("groups")
    val groups: List<ImageGroupResponse>
)