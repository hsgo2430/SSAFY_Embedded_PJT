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
    private  val testConnectUseCase: TestConnectUseCase
): ViewModel(), ContainerHost<SettingState, SettingSideEffect> {

    override val container: Container<SettingState, SettingSideEffect> = container<SettingState, SettingSideEffect> (SettingState())

    fun changeHostIp(hostIp: String) = blockingIntent {
        reduce {
            state.copy(
                hostIP = hostIp
            )
        }
    }

    fun changePath(path: String) = blockingIntent {
        reduce {
            state.copy(
                path = path
            )
        }
    }

    fun testBtnClicked() = intent{
        viewModelScope.launch {
            if(testConnectUseCase(state.hostIP)){
                Log.e("로그", "성공")
            }
            else{
                Log.e("로그", "실패")
            }
        }
    }


}