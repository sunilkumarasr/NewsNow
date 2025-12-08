package com.example.newsatnow.model

import com.google.gson.annotations.SerializedName

data class Category (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null,
  @SerializedName("slug" ) var slug : String? = null

)