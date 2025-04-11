package com.example.lab_4.api

import com.example.lab_4.data.NetworkUserRepository
import com.example.lab_4.data.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val userRepository: UserRepository
}

class ApiClient : AppContainer {
    private val URL = "https://randomuser.me/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL)
        .build()

    private val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(retrofitService)
    }
}