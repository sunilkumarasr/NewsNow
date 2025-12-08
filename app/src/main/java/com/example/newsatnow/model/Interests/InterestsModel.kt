package com.example.example

import com.google.gson.annotations.SerializedName


data class InterestsModel (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("data"    ) var data    : InterestsData?    = InterestsData(),
  @SerializedName("message" ) var message : String?  = null

)