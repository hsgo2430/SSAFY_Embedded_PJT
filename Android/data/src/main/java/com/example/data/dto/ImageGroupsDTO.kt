package com.example.data.dto

import com.example.data.response.ImageGroupResponse
import com.example.data.response.ImageGroupsResponse
import com.example.data.response.ImageItemResponse
import com.example.domain.model.ImageGroup
import com.example.domain.model.ImageGroups
import com.example.domain.model.ImageItem

fun ImageGroupsResponse.toImageGroups() : ImageGroups{


    val list: MutableList<ImageGroup> = mutableListOf()

    this.groups.forEach { it ->

        list.add(
            ImageGroup(
                date = it.date,
                items = it.items.toImageItem()
            )

        )
    }

    return ImageGroups(
        count = this.count,
        groups = list
    )
}

fun List<ImageItemResponse>.toImageItem(): List<ImageItem>{
    val list: MutableList<ImageItem> = mutableListOf()

    this.forEach{ it ->
        list.add(
            ImageItem(
                id = it.id,
                created_at = it.created_at,
                image_url = it.image_url,
                view_url = it.view_url,
                download_url = it.download_url,
                filename = it.filename,
                size_bytes = it.size_bytes,
                latitude = it.latitude ?: "",
                longitude = it.longitude?: ""
            )
        )
    }

    return list
}

