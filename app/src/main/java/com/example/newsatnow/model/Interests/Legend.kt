package com.example.example

import com.google.gson.annotations.SerializedName


data class Legend (

  @SerializedName("manual"   ) var manual   : String? = null,
  @SerializedName("behavior" ) var behavior : String? = null,
  @SerializedName("hybrid"   ) var hybrid   : String? = null

)