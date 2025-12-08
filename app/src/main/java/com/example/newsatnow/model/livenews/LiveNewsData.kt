package com.example.example

import com.google.gson.annotations.SerializedName


data class LiveNewsData (

  @SerializedName("live_news"  ) var liveNews   : ArrayList<LiveNews> = arrayListOf(),
  @SerializedName("pagination" ) var pagination : Pagination?         = Pagination()

)