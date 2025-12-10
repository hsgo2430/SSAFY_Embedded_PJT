package com.example.ssafy.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ssafy.ferature.mqtt.navigator.mqttScreen
import com.example.ssafy.ferature.setting.navigator.settingScreen

const val MQTTSCREEN = "mqtt_screen"
const val SETTINGSCREEN = "setting_screen"

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
        settingScreen(
            modifier = modifier,
            navController = navController
        )

        mqttScreen(
            modifier = modifier,
            navController = navController
        )
    }


}


