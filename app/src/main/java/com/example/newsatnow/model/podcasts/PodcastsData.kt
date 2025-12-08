package com.example.example

import com.google.gson.annotations.SerializedName


data class PodcastsData (

  @SerializedName("podcasts"   ) var podcasts   : ArrayList<Podcasts> = arrayListOf(),
  @SerializedName("pagination" ) var pagination : PodcastsPagination?         = PodcastsPagination()

)