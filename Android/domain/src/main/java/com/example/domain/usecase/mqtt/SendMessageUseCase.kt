package com.example.domain.usecase.mqtt

import com.example.domain.repository.MQTTRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val mqttRepository: MQTTRepository
){
    suspend operator fun invoke(
        message: String
    ){
          return mqttRepository.sendMessage(message)
    }
}