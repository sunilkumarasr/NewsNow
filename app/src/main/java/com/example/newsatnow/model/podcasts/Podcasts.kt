package com.example.example

import com.google.gson.annotations.SerializedName


data class Podcasts (

  @SerializedName("id"             ) var id            : Int?      = null,
  @SerializedName("title"          ) var title         : String?   = null,
  @SerializedName("description"    ) var description   : String?   = null,
  @SerializedName("podcast_type"   ) var podcastType   : String?   = null,
  @SerializedName("media_source"   ) var mediaSource   : String?   = null,
  @SerializedName("podcast_url"    ) var podcastUrl    : String?   = null,
  @SerializedName("cover_image"    ) var coverImage    : String?   = null,
  @SerializedName("thumbnail"      ) var thumbnail     : String?   = null,
  @SerializedName("duration"       ) var duration      : String?   = null,
  @SerializedName("is_featured"    ) var isFeatured    : Boolean?  = null,
  @SerializedName("listens"        ) var listens       : Int?      = null,
  @SerializedName("category"       ) var category      : PodcastsCategory? = PodcastsCategory(),
  @SerializedName("published_at"   ) var publishedAt   : String?   = null,
  @SerializedName("created_at"     ) var createdAt     : String?   = null,
  @SerializedName("updated_at"     ) var updatedAt     : String?   = null,
  @SerializedName("published_time" ) var publishedTime : String?   = null

)