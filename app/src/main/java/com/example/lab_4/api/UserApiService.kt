package com.example.lab_4.api

import retrofit2.Response
import retrofit2.http.GET

interface UserApiService {
    @GET("api/?results=20")
    suspend fun getUsers(): Response<UserResponse>
}