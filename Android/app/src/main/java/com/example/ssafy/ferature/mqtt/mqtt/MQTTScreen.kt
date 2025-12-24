package com.example.ssafy.ferature.mqtt.mqtt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ssafy.R
import com.example.ssafy.ferature.setting.setting.SettingError
import com.example.ssafy.ferature.setting.setting.SettingSideEffect
import com.example.ssafy.ferature.setting.setting.SettingViewModel
import com.example.ssafy.ui.theme.Purple63
import com.example.ssafy.util.showMessage
import org.orbitmvi.orbit.compose.collectSideEffect
import org.webrtc.EglBase
import org.webrtc.SurfaceViewRenderer


@Composable
fun MQTTScreen(
    modifier: Modifier = Modifier,
    mqttViewModel: MQTTViewModel = hiltViewModel(),
    navigateToAlbumScreen:() -> Unit = {}
) {

    val state = mqttViewModel.collectAsState().value

    MQTTScreenImpl(
        modifier = modifier,
        state = state,
        sendBtnClicked  = mqttViewModel::sendBtnClicked,
        onChangeMessage = mqttViewModel::onChangeMessage,
        onChangeHostIP = mqttViewModel::onChangeHostIP,
        albumBtnClicked = mqttViewModel::albumBtnClicked

    )
    HandleSideEffects(mqttViewModel, navigateToAlbumScreen)
}

@Composable
fun MQTTScreenImpl(
    modifier: Modifier = Modifier,

    state: MQTTState = MQTTState(),
    sendBtnClicked : (String) -> Unit = {},
    onChangeMessage: (String) -> Unit = {},
    onChangeHostIP: (String) -> Unit = {},
    albumBtnClicked: () -> Unit = {},
    piIP: String = "192.168.137.138"
) {
    val context = LocalContext.current

    // 🔥 EGL Base 1개만 생성해야 함
    val eglBase = remember { EglBase.create() }

    val renderer = remember {
        SurfaceViewRenderer(context)
    }

    // 🔥 Client는 동일 eglBase 사용
    val whepClient = remember {
        MediamtxWhepClient(renderer, eglBase)
    }


    Column(
        modifier = modifier.fillMaxSize()
    ) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                factory = {
                    renderer.apply {
                        init(eglBase.eglBaseContext, null)
                        setEnableHardwareScaler(true)
                        setMirror(false)
                    }
                }
            )


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
                .clickable {
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
                    .clickable {
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
                    .clickable {
                        sendBtnClicked("stop")
                    }
                ,
                painter = painterResource(R.drawable.stop_indicator),
                contentDescription = ""
            )
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
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
                .clickable {
                    sendBtnClicked("back")
                }
                .align(Alignment.CenterHorizontally)
            ,
            painter = painterResource(R.drawable.backward_button),
            contentDescription = ""
        )

        Button(
            onClick = { albumBtnClicked() },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple63
            )
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Album",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        LaunchedEffect(piIP) {
            whepClient.start("http://$piIP:8889/cam1/whep")
        }

        DisposableEffect(Unit) {
            onDispose {
                whepClient.release()
                eglBase.release()
            }
        }
    }
}

@Composable
fun HandleSideEffects(
    viewModel: MQTTViewModel,
    navigateToAlbumScreen:() -> Unit = {}
) {

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is MQTTSideEffect.NavigateAlbum ->{
                navigateToAlbumScreen()
            }
        }
    }
}

@Preview
@Composable
fun MQTTScreenPreview(modifier: Modifier = Modifier) {
    MQTTScreenImpl()
}