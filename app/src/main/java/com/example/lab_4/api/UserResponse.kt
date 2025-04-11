package com.example.lab_4.api

import com.example.lab_4.data.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results")
    val results: List<User>
)
