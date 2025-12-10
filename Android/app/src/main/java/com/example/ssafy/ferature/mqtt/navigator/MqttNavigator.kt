package com.example.ssafy.ferature.mqtt.navigator

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ssafy.ferature.mqtt.mqtt.MQTTScreen
import com.example.ssafy.main.MQTTSCREEN

fun NavGraphBuilder.mqttScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    navigation(
        route = MQTTSCREEN,
        startDestination = MQTTRoute.MQTTScreenRoute.name
    ){
        composable(
            route = MQTTRoute.MQTTScreenRoute.name
        ) {
            MQTTScreen(
                modifier = modifier
            )
        }
    }
}