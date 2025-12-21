package com.example.ssafy.util

import android.widget.Toast
import com.example.ssafy.MQTTApplication

fun showMessage(message: String){
    Toast.makeText(MQTTApplication.instance, message, Toast.LENGTH_LONG).show()
}