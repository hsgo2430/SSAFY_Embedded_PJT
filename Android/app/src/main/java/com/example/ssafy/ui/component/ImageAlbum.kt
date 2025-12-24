package com.example.ssafy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.example.domain.model.ImageItem
import com.example.ssafy.ui.theme.DividerColor

@Composable
fun AlbumComponent(
    modifier: Modifier = Modifier,
    dateString: String = "2025-12-25",
    imageList: List<ImageItem> = emptyList(),
) {
    val columns = 3
    val itemSize = 100.dp
    val spacing = 10.dp
    val rows = (imageList.size + columns - 1) / columns
    val gridHeight =
        if (rows == 0) 0.dp
        else rows * itemSize + (rows - 1) * spacing + 20.dp // contentPadding(10.dp) 상+하

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
    ) {
        Text(dateString, fontSize = 24.sp, fontWeight = Bold)

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp),
            color = DividerColor
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(gridHeight),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalArrangement = Arrangement.spacedBy(spacing),
        ) {
            items(imageList, key = { it.id }) { image ->
                AsyncImage(
                    model = "https://5d64831169ed.ngrok-free.app${image.image_url}",
                    placeholder = painterResource(com.example.ssafy.R.drawable.left_button),
                    contentDescription = "아이템 이미지",
                    modifier = Modifier.size(itemSize)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewAlbumComponent(
    modifier: Modifier = Modifier
) {
    val list: MutableList<ImageItem> = mutableListOf()
    list.add(
        ImageItem(
            8,
       "2025-12-24 09:27:35",
        "/image/8",
        "/view/8",
        "/download/8",
        "utility_knife_1766533527.jpg",
        820299,
        "",
        ""
        )
    )
    list.add(
        ImageItem(
            7,
            "2025-12-24 09:27:35",
            "/image/7",
            "/view/8",
            "/download/8",
            "utility_knife_1766533527.jpg",
            820299,
            "",
            ""
        )
    )
    list.add(
        ImageItem(
            6,
            "2025-12-24 09:27:35",
            "/image/6",
            "/view/8",
            "/download/8",
            "utility_knife_1766533527.jpg",
            820299,
            "",
            ""
        )
    )
    AlbumComponent(
        imageList = list
    )
}

