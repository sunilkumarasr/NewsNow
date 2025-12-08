package com.example.example

import com.google.gson.annotations.SerializedName


data class TrendingData (
  @SerializedName("trending_articles" ) var trendingArticles : ArrayList<TrendingArticles> = arrayListOf()
)
