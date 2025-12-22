package com.example.ssafy.ferature.setting.setting

sealed class SettingSideEffect {

    data object ConnectMQTT: SettingSideEffect()
    data object SuccessTest: SettingSideEffect()
    data class ShowMessage(val type: SettingError): SettingSideEffect()

}

enum class SettingError{
    FailTest,
    NotConnect,
    BlankIP,
    BlankPath
}
