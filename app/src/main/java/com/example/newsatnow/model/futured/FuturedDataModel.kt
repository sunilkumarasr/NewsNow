package com.example.example

import com.google.gson.annotations.SerializedName


data class FuturedDataModel (

  @SerializedName("success" ) var success : String? = null,
  @SerializedName("data"    ) var data    : FuturedData?    = FuturedData(),
  @SerializedName("message" ) var message : String?  = null

)