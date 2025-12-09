package com.example.ssafy.ferature.mqtt.mqtt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun MQTTScreen(
    modifier: Modifier = Modifier,
    mqttViewModel: MQTTViewModel = hiltViewModel()
) {

    val state = mqttViewModel.collectAsState().value

    MQTTScreenImpl(
        modifier = modifier,
        state = state,
        sendBtnClicked  = mqttViewModel::sendBtnClicked
    )
}

@Composable
fun MQTTScreenImpl(
    modifier: Modifier = Modifier,
    state: MQTTState = MQTTState(),
    sendBtnClicked : (String) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    var lastMessage by remember { mutableStateOf("no message") }


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Last message: $lastMessage")

        Button(onClick = {
            sendBtnClicked("장애물")
        }) {
            Text("보내기")
        }

    }
}

@Preview
@Composable
fun MQTTScreenPreview(modifier: Modifier = Modifier) {
    MQTTScreenImpl()
}