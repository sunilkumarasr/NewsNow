package com.example.example

import com.google.gson.annotations.SerializedName


data class RegisterPostData (

  @SerializedName("name"  ) var name  : String? = null,
  @SerializedName("email" ) var email : String? = null,
  @SerializedName("phone" ) var phone : String? = null,
  @SerializedName("password" ) var password : String? = null

)