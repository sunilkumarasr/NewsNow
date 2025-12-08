package com.example.example

import com.google.gson.annotations.SerializedName


data class FuturedData (
  @SerializedName("featured_articles" ) var trendingArticles : ArrayList<FeaturedArticles> = arrayListOf()
)
