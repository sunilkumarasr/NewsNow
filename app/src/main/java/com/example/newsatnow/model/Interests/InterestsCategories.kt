package com.example.example

import com.google.gson.annotations.SerializedName


data class InterestsCategories (

  @SerializedName("id"                ) var id               : Int?     = null,
  @SerializedName("name"              ) var name             : String?  = null,
  @SerializedName("slug"              ) var slug             : String?  = null,
  @SerializedName("articles_count"    ) var articlesCount    : Int?     = null,
  @SerializedName("icon"              ) var icon             : String?  = null

)