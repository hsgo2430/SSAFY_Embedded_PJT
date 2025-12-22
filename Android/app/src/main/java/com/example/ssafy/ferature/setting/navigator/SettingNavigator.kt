package com.example.ssafy.ferature.setting.navigator

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ssafy.ferature.setting.setting.SettingScreen
import com.example.ssafy.main.SETTINGSCREEN

fun NavGraphBuilder.settingScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    navigation(
        route = SETTINGSCREEN,
        startDestination = SettingRoute.SettingScreenRoute.name
    ){
        composable(
            route = SettingRoute.SettingScreenRoute.name
        ) {
            SettingScreen(
                modifier = modifier
            )
        }
    }
}