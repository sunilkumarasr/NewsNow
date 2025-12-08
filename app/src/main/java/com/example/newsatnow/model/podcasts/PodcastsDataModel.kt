package com.example.example

import com.google.gson.annotations.SerializedName


data class PodcastsDataModel (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("data"    ) var data    : PodcastsData?    = PodcastsData(),
  @SerializedName("message" ) var message : String?  = null

)