package com.example.ssafy.ferature.mqtt.navigator

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.ssafy.ferature.mqtt.mqtt.MQTTScreen
import com.example.ssafy.main.ALBUMSCREEN
import com.example.ssafy.main.MQTTSCREEN

fun NavGraphBuilder.mqttScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    navigation(
        route = MQTTSCREEN,
        startDestination = "${MQTTRoute.MQTTScreenRoute.name}/{hostIP}"
    ){
        composable(
            route = "${MQTTRoute.MQTTScreenRoute.name}/{hostIP}",
            arguments = listOf(
                navArgument("hostIP"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            MQTTScreen(
                modifier = modifier,
                navigateToAlbumScreen = {
                    navController.navigate(ALBUMSCREEN)
                }
            )
        }
    }
}