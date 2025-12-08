package com.example.example

import com.google.gson.annotations.SerializedName


data class LoginPostData (

  @SerializedName("email" ) var email : String? = null,
  @SerializedName("password" ) var password  : String? = null

)