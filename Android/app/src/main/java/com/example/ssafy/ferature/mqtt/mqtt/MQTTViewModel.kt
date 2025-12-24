package com.example.ssafy.ferature.mqtt.mqtt

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.mqtt.ConnectMqttUseCase
import com.example.domain.usecase.mqtt.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class MQTTViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sendMessageUseCase: SendMessageUseCase,
    private val connectMqttUseCase: ConnectMqttUseCase
):ViewModel(), ContainerHost<MQTTState, MQTTSideEffect> {
    override val container: Container<MQTTState, MQTTSideEffect>  = container<MQTTState, MQTTSideEffect>(MQTTState())

    private val hostIP: String = checkNotNull(savedStateHandle["hostIP"])


    init{
        saveHostIP()
    }
    private fun saveHostIP(){
        intent{
            reduce {
                state.copy(
                    hostIP = hostIP
                )
            }
        }
    }
    fun connectBtnClicked(
        hostIP: String
    ){
        intent {
            viewModelScope.launch(Dispatchers.IO) {
                connectMqttUseCase(hostIP)
            }
        }
    }

    fun sendBtnClicked(
        message: String
    ){
        intent {
            viewModelScope.launch(Dispatchers.IO) {
                sendMessageUseCase(message)
            }
        }
    }

    fun onChangeMessage(
        message: String
    ) = blockingIntent{
        reduce {
            state.copy(
                message = message
            )
        }
    }

    fun onChangeHostIP(
        hostIP: String
    ) = blockingIntent{
        reduce {
            state.copy(
               hostIP = hostIP
            )
        }
    }

    fun albumBtnClicked() = intent{
        postSideEffect(MQTTSideEffect.NavigateAlbum)
    }
}