package com.example.domain.usecase.mqtt

import com.example.domain.repository.MQTTRepository
import javax.inject.Inject

class ConnectMqttUseCase @Inject constructor(
    private val mqttRepository: MQTTRepository
){
    suspend operator fun invoke(
        hostIP: String
    ): Boolean{
        return mqttRepository.mqttConnect(hostIP)
    }

}