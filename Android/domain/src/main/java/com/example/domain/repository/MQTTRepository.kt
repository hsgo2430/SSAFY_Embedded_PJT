package com.example.domain.repository

interface MQTTRepository {
    suspend fun mqttConnect(message:String)

    suspend fun sendMessage(message:String)
}