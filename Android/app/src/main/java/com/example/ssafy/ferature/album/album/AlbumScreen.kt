package com.example.ssafy.ferature.album.album

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ssafy.R
import com.example.ssafy.ui.component.AlbumComponent
import com.example.ssafy.ui.theme.Purple63
import com.example.ssafy.ui.theme.PurpleE0
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun AlbumScreen(
    modifier: Modifier = Modifier,
    albumViewModel: AlbumViewModel = hiltViewModel()
) {
    val state = albumViewModel.collectAsState().value
    AlbumScreenImpl(
        modifier = modifier,
        state = state
    )
}

@Composable
fun AlbumScreenImpl(
    modifier: Modifier = Modifier,
    state: AlbumState = AlbumState()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(93.dp)
                .background(color = Purple63),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(20.dp),
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = ""
                    )

                    Text(
                        text = "Setting",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    text = "Album",
                    color = PurpleE0
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(state.imageGroups.groups){ it ->
                AlbumComponent(
                    modifier = Modifier,
                    dateString = it.date,
                    imageList = it.items
                )
            }
        }
    }
}



@Preview
@Composable
fun PreviewAlbumScreen(
    modifier: Modifier = Modifier
){
    AlbumScreenImpl()
}