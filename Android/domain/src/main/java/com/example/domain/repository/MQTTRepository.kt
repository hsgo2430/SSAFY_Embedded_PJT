package com.example.domain.repository

interface MQTTRepository {
    suspend fun mqttConnect(hostIP:String)

    suspend fun sendMessage(message:String)
}