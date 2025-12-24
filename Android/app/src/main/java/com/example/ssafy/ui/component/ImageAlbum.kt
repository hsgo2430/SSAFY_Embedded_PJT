package com.example.ssafy.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import com.example.domain.model.ImageItem
import com.example.ssafy.ui.theme.DividerColor

@Composable
fun AlbumComponent(
    modifier: Modifier = Modifier,
    dateString: String = "2025-12-25",
    imageList: List<ImageItem> = emptyList(),
) {
    // ✅ 클릭한 이미지 저장
    var selected by rememberSaveable { mutableStateOf<ImageItem?>(null) }

    val columns = 3
    val itemSize = 100.dp
    val spacing = 10.dp
    val rows = (imageList.size + columns - 1) / columns
    val gridHeight =
        if (rows == 0) 0.dp
        else rows * itemSize + (rows - 1) * spacing + 20.dp

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
                    placeholder = painterResource(com.example.ssafy.R.drawable.loading),
                    contentDescription = "아이템 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(itemSize)
                        .clickable { selected = image }
                )
            }
        }
    }

    if (selected != null) {
        Dialog(onDismissRequest = { selected = null }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { selected = null },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {  }
                ) {
                    AsyncImage(
                        model = "https://5d64831169ed.ngrok-free.app${selected!!.image_url}",
                        placeholder = painterResource(com.example.ssafy.R.drawable.loading),
                        contentDescription = "확대 이미지",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f) // 원하면 제거 가능
                    )

                    IconButton(
                        onClick = { selected = null },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "닫기",
                            tint = Color.White
                        )
                    }
                }
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

