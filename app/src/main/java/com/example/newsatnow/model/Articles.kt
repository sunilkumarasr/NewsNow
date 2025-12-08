package com.example.example

import com.example.newsatnow.model.Category
import com.google.gson.annotations.SerializedName


data class Articles (

  @SerializedName("id"          ) var id         : Int?      = null,
  @SerializedName("title"       ) var title      : String?   = null,
  @SerializedName("summary"     ) var summary    : String?   = null,
  @SerializedName("image"       ) var image      : String?   = null,
  @SerializedName("video"       ) var video      : String?   = null,
  @SerializedName("voiceover"   ) var voiceover  : String?   = null,
  @SerializedName("source"      ) var source     : String?   = null,
  @SerializedName("reporter"    ) var reporter   : String?   = null,
  @SerializedName("is_featured" ) var isFeatured : Boolean?  = null,
  @SerializedName("is_trending" ) var isTrending : Boolean?  = null,
  @SerializedName("category"    ) var category   : Category? = Category(),
  @SerializedName("created_at"  ) var createdAt  : String?   = null,
  @SerializedName("updated_at"  ) var updatedAt  : String?   = null

)