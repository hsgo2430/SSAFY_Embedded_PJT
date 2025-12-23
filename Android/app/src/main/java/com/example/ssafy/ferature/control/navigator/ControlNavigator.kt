package com.example.ssafy.ferature.control.navigator

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.ssafy.ferature.control.control.ControlScreen
import com.example.ssafy.ferature.mqtt.mqtt.MQTTScreen
import com.example.ssafy.main.CONTROLSCREEN
import com.example.ssafy.main.MQTTSCREEN

fun NavGraphBuilder.controlScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    navigation(
        route = CONTROLSCREEN,
        startDestination = ControlRoute.ControlScreenRoute.name
    ){
        composable(
            route = ControlRoute.ControlScreenRoute.name,
        ) {
            ControlScreen(
                modifier = modifier
            )
        }
    }
}