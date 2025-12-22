package com.example.ssafy.ferature.setting.setting

data class SettingState (
    val hostIP: String = "",
    val path: String = "",
    val isChecked: Boolean = true,
    val isHostIPBlank: Boolean = true,
    val isPathBlank: Boolean = true
)