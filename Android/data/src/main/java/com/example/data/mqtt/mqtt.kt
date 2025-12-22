package com.example.data.mqtt

import android.util.Log
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.StandardCharsets
import java.util.UUID

object MqttClientHelper {
    private const val TAG = "MQTT"

    private const val BROKER_PORT = 1883

    private var client: Mqtt5AsyncClient? = null

    suspend fun test(hostIP: String): Boolean = withContext(Dispatchers.IO) {
        var testClient: Mqtt5AsyncClient? = null
        runCatching {

           testClient = MqttClient.builder()
                .useMqttVersion5()
                .identifier("android-${UUID.randomUUID()}")
                .serverHost(hostIP)
                .serverPort(BROKER_PORT)
                .buildAsync()

            testClient!!.connect().join()
        }.onSuccess {
            Log.d(TAG, "MQTT connected")
            testClient!!.disconnect().join()
        }.onFailure {
            Log.e(TAG, "MQTT connect error", it)
        }.isSuccess
    }

    suspend fun connect(hostIP: String): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            client?.disconnect()
            client = null

            client = MqttClient.builder()
                .useMqttVersion5()
                .identifier("android-${UUID.randomUUID()}")
                .serverHost(hostIP)
                .serverPort(BROKER_PORT)
                .buildAsync()

            client!!.connect().join()
        }.onSuccess {
            Log.d(TAG, "MQTT connected")
        }.onFailure {
            Log.e(TAG, "MQTT connect error", it)
        }.isSuccess
    }

    /** 토픽 구독 + 메시지 콜백 */
    fun subscribe(topicFilter: String, onMessage: (topic: String, payload: String) -> Unit) {
        client!!.subscribeWith()
            .topicFilter(topicFilter)
            .qos(MqttQos.AT_LEAST_ONCE)
            .callback { publish ->
                val topic = publish.topic.toString()
                val msg = publish.payload
                    .map { it.array() }
                    .orElse(ByteArray(0))
                    .toString(StandardCharsets.UTF_8)

                Log.d(TAG, "Received [$topic] $msg")
                onMessage(topic, msg)
            }
            .send()
    }

    /** 메시지 발행 */
    fun publish(topic: String, payload: String) {
        client!!.publishWith()
            .topic(topic)
            .qos(MqttQos.AT_LEAST_ONCE)
            .payload(payload.toByteArray(StandardCharsets.UTF_8))
            .send()
    }

    /** 연결 끊기 (앱 종료 시 등) */
    fun disconnect() {
        client?.disconnect()
    }
}