package com.example.ssafy.ferature.mqtt.mqtt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import com.example.ssafy.R


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


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = Color.Black)
        ){

        }

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



        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            text = "RC Car Controller",
            fontSize = 24.sp,
            textAlign = Center,
            fontWeight = Bold
        )

        Image(
            modifier = Modifier
                .size(80.dp)
                .clickable{
                    sendBtnClicked("go")
                }
                .align(Alignment.CenterHorizontally)
            ,
            painter = painterResource(R.drawable.forward_button),
            contentDescription = ""
        )

        Row(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
        ){
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clickable{
                        sendBtnClicked("left")
                    }
                ,
                painter = painterResource(R.drawable.left_button),
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .size(80.dp)
                    .clickable{
                        sendBtnClicked("stop")
                    }
                ,
                painter = painterResource(R.drawable.stop_indicator),
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clickable{
                        sendBtnClicked("right")
                    }
                ,
                painter = painterResource(R.drawable.right_button),
                contentDescription = ""
            )
        }
        Image(
            modifier = Modifier
                .size(80.dp)
                .clickable{
                    sendBtnClicked("back")
                }
                .align(Alignment.CenterHorizontally)
            ,
            painter = painterResource(R.drawable.backward_button),
            contentDescription = ""
        )



//        Button(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            onClick = {
//                sendBtnClicked("go")
//            }
//        ) {
//            Text("전진")
//        }
//
//        Button(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            onClick = {
//                sendBtnClicked("back")
//            }
//        ) {
//            Text("후진")
//        }
//
//        Button(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            onClick = {
//                sendBtnClicked("right")
//            }
//        ) {
//            Text("좌회전")
//        }
//
//        Button(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            onClick = {
//                sendBtnClicked("left")
//            }
//        ) {
//            Text("우회전")
//        }
//
//        Button(
//            modifier = Modifier
//                .padding(top = 20.dp)
//                .fillMaxWidth(),
//            onClick = {
//                sendBtnClicked("stop")
//            }
//        ) {
//            Text("정지")
//        }

    }
}

@Preview
@Composable
fun MQTTScreenPreview(modifier: Modifier = Modifier) {
    MQTTScreenImpl()
}