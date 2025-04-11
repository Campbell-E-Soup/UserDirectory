package com.example.lab_4.data

import com.example.lab_4.api.UserApiService

interface UserRepository {
    //get users
    suspend fun getUsers(): List<User>?
}

class NetworkUserRepository(
    private val userApiService: UserApiService
) : UserRepository {
    //get lists of users
    override suspend fun getUsers(): List<User>? = userApiService.getUsers().body()?.results
}