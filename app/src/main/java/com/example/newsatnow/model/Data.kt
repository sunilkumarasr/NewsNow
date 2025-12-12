package com.example.example

import com.example.newsatnow.model.Category
import com.google.gson.annotations.SerializedName


data class Data (
  @SerializedName("categories"        ) var categories       : ArrayList<Category>       = arrayListOf(),
  @SerializedName("featured_articles" ) var featuredArticles : ArrayList<FeaturedArticles> = arrayListOf(),
  @SerializedName("trending_articles" ) var trendingArticles : ArrayList<TrendingArticles> = arrayListOf()
)
