package com.example.example

import com.google.gson.annotations.SerializedName


data class MobileOTPRequestPostModel (

  @SerializedName("phone" ) var phone : String? = null

)