package com.example.ssafy.ferature.mqtt.mqtt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        sendBtnClicked  = mqttViewModel::sendBtnClicked,
        onChangeMessage = mqttViewModel::onChangeMessage,
        onChangeHostIP = mqttViewModel::onChangeHostIP,
        connectBtnClicked = mqttViewModel::connectBtnClicked

    )
}

@Composable
fun MQTTScreenImpl(
    modifier: Modifier = Modifier,

    state: MQTTState = MQTTState(),
    sendBtnClicked : (String) -> Unit = {},
    onChangeMessage: (String) -> Unit = {},
    onChangeHostIP: (String) -> Unit = {},
    connectBtnClicked: (String) -> Unit = {}
) {
    var lastMessage by remember { mutableStateOf("no message") }


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Last message: $lastMessage")


//        TextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = state.hostIP,
//            onValueChange = onChangeHostIP
//        )
//
//        Button(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            onClick = {
//                connectBtnClicked(state.hostIP)
//            }
//        ) {
//            Text("연결하기")
//        }
//
//
//        TextField(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            value = state.message,
//            onValueChange = onChangeMessage
//        )
//
//        Button(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            onClick = {
//                sendBtnClicked(state.message)
//            }
//        ) {
//            Text("보내기")
//        }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                sendBtnClicked("go")
            }
        ) {
            Text("전진")
        }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                sendBtnClicked("back")
            }
        ) {
            Text("후진")
        }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                sendBtnClicked("right")
            }
        ) {
            Text("좌회전")
        }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                sendBtnClicked("left")
            }
        ) {
            Text("우회전")
        }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                sendBtnClicked("stop")
            }
        ) {
            Text("정지")
        }

    }
}

@Preview
@Composable
fun MQTTScreenPreview(modifier: Modifier = Modifier) {
    MQTTScreenImpl()
}