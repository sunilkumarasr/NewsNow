package com.example.example

import com.google.gson.annotations.SerializedName


data class LoginModel (

  @SerializedName("token" ) var token : String? = null,
  @SerializedName("user"  ) var user  : User?   = User()

)