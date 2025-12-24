package com.example.ssafy.ferature.mqtt.mqtt

sealed class MQTTSideEffect {
    data object NavigateAlbum: MQTTSideEffect()
}