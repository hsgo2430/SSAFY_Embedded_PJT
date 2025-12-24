package com.example.ssafy.ferature.album.navigator

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ssafy.ferature.album.album.AlbumScreen
import com.example.ssafy.main.ALBUMSCREEN

fun NavGraphBuilder.albumScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    navigation(
        route = ALBUMSCREEN,
        startDestination = AlbumRoute.AlbumScreenRoute.name
    ){
        composable(
            route = AlbumRoute.AlbumScreenRoute.name,
        ) {
            AlbumScreen(
                modifier = modifier
            )
        }
    }
}