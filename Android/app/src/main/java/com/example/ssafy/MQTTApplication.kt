package com.example.ssafy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MQTTApplication: Application() {
    companion object{
        lateinit var instance:  MQTTApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}