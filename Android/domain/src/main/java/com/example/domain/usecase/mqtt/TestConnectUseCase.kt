package com.example.domain.usecase.mqtt

import com.example.domain.repository.MQTTRepository
import javax.inject.Inject

class TestConnectUseCase @Inject constructor(
    private val mqttRepository: MQTTRepository
) {
    suspend operator fun invoke(
        hostIp: String
    ): Boolean {
        return mqttRepository.testConnect(hostIp)
    }
}