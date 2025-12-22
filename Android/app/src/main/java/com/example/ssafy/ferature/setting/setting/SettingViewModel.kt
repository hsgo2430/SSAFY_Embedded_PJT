package com.example.ssafy.ferature.setting.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.mqtt.ConnectMqttUseCase
import com.example.domain.usecase.mqtt.TestConnectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private  val testConnectUseCase: TestConnectUseCase,
    private val connectMqttUseCase: ConnectMqttUseCase
): ViewModel(), ContainerHost<SettingState, SettingSideEffect> {

    override val container: Container<SettingState, SettingSideEffect> = container<SettingState, SettingSideEffect> (SettingState())

    fun changeHostIp(hostIp: String) = blockingIntent {
        reduce {
            state.copy(
                hostIP = hostIp,
                isHostIPBlank = hostIp.isBlank()
            )
        }
    }

    fun changePath(path: String) = blockingIntent {
        reduce {
            state.copy(
                path = path,
                isPathBlank = path.isBlank()
            )
        }
    }

    fun testBtnClicked() = intent{
        if(state.isHostIPBlank){
            postSideEffect(SettingSideEffect.ShowMessage(SettingError.BlankIP))
        }
        else if(state.isPathBlank){
            postSideEffect(SettingSideEffect.ShowMessage(SettingError.BlankPath))
        }
        else {
            viewModelScope.launch {
                if (testConnectUseCase(state.hostIP)) {
                    postSideEffect(SettingSideEffect.SuccessTest)
                } else {
                    postSideEffect(SettingSideEffect.ShowMessage(SettingError.FailTest))
                }
            }
        }
    }

    fun saveBtnClicked() = intent{
        viewModelScope.launch {
            if(connectMqttUseCase(state.hostIP)){
                postSideEffect(SettingSideEffect.ConnectMQTT)
            }
            else{
                postSideEffect(SettingSideEffect.ShowMessage(SettingError.NotConnect))
            }
        }
    }


}