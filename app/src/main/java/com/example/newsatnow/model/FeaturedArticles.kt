package com.example.example

import com.example.newsatnow.model.Category
import com.google.gson.annotations.SerializedName


data class FeaturedArticles (

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
  @SerializedName("updated_at"  ) var updatedAt  : String?   = null,
  @SerializedName("likes"          ) var likes         : Int?              = null,
  @SerializedName("dislikes"       ) var dislikes      : Int?              = null,
  @SerializedName("shares"         ) var shares        : Int?              = null,
  @SerializedName("views"          ) var views         : Int?              = null,
  @SerializedName("comments"       ) var comments      : ArrayList<String> = arrayListOf(),
  @SerializedName("comments_count" ) var commentsCount : Int?              = null

)