package com.example.example

import com.google.gson.annotations.SerializedName


data class ArticalCategory (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null,
  @SerializedName("slug" ) var slug : String? = null

)