package com.example.example

import com.google.gson.annotations.SerializedName


data class User (

  @SerializedName("id"    ) var id    : Int?    = null,
  @SerializedName("name"  ) var name  : String? = null,
  @SerializedName("email" ) var email : String? = null,
  @SerializedName("phone" ) var phone : String? = null

)