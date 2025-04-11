package com.example.lab_4

import android.app.Application
import com.example.lab_4.api.ApiClient
import com.example.lab_4.api.AppContainer

class UserApplication : Application() { //the application
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = ApiClient()
    }
}