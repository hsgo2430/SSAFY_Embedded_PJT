package com.example.ssafy.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ssafy.ferature.MqttClientHelper
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


