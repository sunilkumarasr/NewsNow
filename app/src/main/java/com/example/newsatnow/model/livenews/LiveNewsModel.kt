package com.example.example

import com.google.gson.annotations.SerializedName


data class LiveNewsModel (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("data"    ) var data    : LiveNewsData?    = LiveNewsData(),
  @SerializedName("message" ) var message : String?  = null

)