package com.example.ssafy.ferature.setting.navigator

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.example.ssafy.ferature.mqtt.navigator.MQTTRoute
import com.example.ssafy.ferature.setting.setting.SettingScreen
import com.example.ssafy.main.MQTTSCREEN
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
                modifier = modifier,
                navigateToVideoScreen = { hostIP ->
                    navController.navigateToMQTTScreen(hostIP)
                }
            )
        }
    }
}

fun NavController.navigateToMQTTScreen(hostIP: String) {
    this.navigate("${MQTTRoute.MQTTScreenRoute.name}/$hostIP")
}