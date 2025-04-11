package com.example.lab_4.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("picture")
    val picture: Picture
)
