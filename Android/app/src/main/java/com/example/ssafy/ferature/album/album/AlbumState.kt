package com.example.ssafy.ferature.album.album

import com.example.domain.model.ImageGroups
import com.example.domain.model.ImageItem

data class AlbumState(
    val imageGroups: ImageGroups = ImageGroups(0, emptyList())
)