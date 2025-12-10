package com.example.ssafy.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ssafy.ferature.mqtt.navigator.mqttScreen

const val MQTTSCREEN = "mqtt_screen"

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = "",
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        mqttScreen(
            modifier = modifier,
            navController = navController
        )
    }


}


