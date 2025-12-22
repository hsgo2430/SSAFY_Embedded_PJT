package com.example.ssafy.ferature.control.control

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.webrtc.EglBase
import org.webrtc.SurfaceViewRenderer

@Composable
fun ControlScreen(
    modifier: Modifier = Modifier
) {

}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ControlScreenImpl(
    modifier: Modifier = Modifier,
    piIP: String = "192.168.45.240"
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

    Column(modifier) {

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

        Text("Camera Streaming")
    }

    // WebRTC 연결 시작
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


@Preview
@Composable
fun PreviewControlScreen(
    modifier: Modifier = Modifier
) {

    ControlScreenImpl()

}