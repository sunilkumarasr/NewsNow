package com.example.example

import com.google.gson.annotations.SerializedName


data class NewsScreenModel (

  @SerializedName("success" ) var success : String? = null,
  @SerializedName("data"    ) var data    : Data?    = Data(),
  @SerializedName("message" ) var message : String?  = null

)