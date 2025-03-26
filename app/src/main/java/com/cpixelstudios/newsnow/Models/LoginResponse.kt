package com.cpixelstudios.newsnow.Models

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("user") val user: LoginResponse,
)
data class LoginResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: Int,
    @SerializedName("email") val email: Int,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("created_at") val created_at: String,
)

data class LoginRequest(
    val email: String,
    val password: String
)
