package com.example.example

import com.google.gson.annotations.SerializedName


data class PodcastsCategory (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null,
  @SerializedName("slug" ) var slug : String? = null

)