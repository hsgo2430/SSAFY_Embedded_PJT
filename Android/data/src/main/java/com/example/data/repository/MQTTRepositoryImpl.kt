package com.example.data.repository

import com.example.data.mqtt.MqttClientHelper
import com.example.domain.repository.MQTTRepository

class MQTTRepositoryImpl: MQTTRepository {

    override suspend fun mqttConnect(hostIP: String) {
        MqttClientHelper.connect(hostIP)
    }

    override suspend fun sendMessage(message: String) {
        MqttClientHelper.publish(
                "KFC",
                message
            )
    }
}