package com.example.example

import com.google.gson.annotations.SerializedName


data class ArticalDataModel (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("data"    ) var data    : ArticalData?    = ArticalData(),
  @SerializedName("message" ) var message : String?  = null

)