package com.example.ssafy.ferature.setting.setting

sealed class SettingSideEffect {

    data object ConnectMQTTL: SettingSideEffect()
    data object SuccessTest: SettingSideEffect()
    data object ShowMessage: SettingSideEffect()

}

enum class SettingError{
    FailTest,
    NotConnect,
    BlankIP,
    BlankPath
}
