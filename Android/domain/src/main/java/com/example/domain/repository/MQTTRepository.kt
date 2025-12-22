package com.example.domain.repository

interface MQTTRepository {
    suspend fun testConnect(hostIP: String): Boolean

    suspend fun mqttConnect(hostIP:String): Boolean

    suspend fun sendMessage(message:String)
}