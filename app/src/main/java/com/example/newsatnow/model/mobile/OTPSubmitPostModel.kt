package com.example.example

import com.google.gson.annotations.SerializedName


data class OTPSubmitPostModel (

  @SerializedName("phone" ) var phone : String? = null,
  @SerializedName("otp"   ) var otp   : String? = null

)