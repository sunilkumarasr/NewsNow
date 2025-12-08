package com.example.example

import com.google.gson.annotations.SerializedName


data class TrendingDataModel (

  @SerializedName("success" ) var success : String? = null,
  @SerializedName("data"    ) var data    : TrendingData?    = TrendingData(),
  @SerializedName("message" ) var message : String?  = null

)