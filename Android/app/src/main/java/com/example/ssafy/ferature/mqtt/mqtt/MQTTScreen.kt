package com.example.ssafy.ferature.mqtt.mqtt

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ssafy.ferature.MqttClientHelper

@Composable
fun MQTTScreen(
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var lastMessage by remember { mutableStateOf("no message") }

    LaunchedEffect(Unit) {
        MqttClientHelper.connect()
    }

    Column(
        modifier = modifier
    ) {
        Text(text = "Last message: $lastMessage")

        Button(onClick = {
            MqttClientHelper.publish(
                "KFC",
                "장매물 발견"
            )
        }) {
            Text("보내기")
        }

    }

}

@Preview
@Composable
fun MQTTScreenPreview(modifier: Modifier = Modifier) {
    MQTTScreen()
}