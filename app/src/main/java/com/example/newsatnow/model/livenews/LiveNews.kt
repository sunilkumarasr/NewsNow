package com.example.example

import com.google.gson.annotations.SerializedName


data class LiveNews (

  @SerializedName("id"          ) var id         : Int?     = null,
  @SerializedName("title"       ) var title      : String?  = null,
  @SerializedName("content"     ) var content    : String?  = null,
  @SerializedName("is_live"     ) var isLive     : Boolean? = null,
  @SerializedName("is_breaking" ) var isBreaking : Boolean? = null,
  @SerializedName("image_url"   ) var imageUrl   : String?  = null,
  @SerializedName("video_url"   ) var videoUrl   : String?  = null,
  @SerializedName("thumbnail"   ) var thumbnail  : String?  = null,
  @SerializedName("location"    ) var location   : String?  = null,
  @SerializedName("started_at"  ) var startedAt  : String?  = null,
  @SerializedName("ended_at"    ) var endedAt    : String?  = null,
  @SerializedName("duration"    ) var duration   : String?  = null,
  @SerializedName("status"      ) var status     : String?  = null

)