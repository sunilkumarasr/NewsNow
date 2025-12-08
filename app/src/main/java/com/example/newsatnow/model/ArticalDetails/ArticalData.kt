package com.example.example

import com.google.gson.annotations.SerializedName


data class ArticalData (

  @SerializedName("article"          ) var article         : Article?                   = Article(),
  @SerializedName("related_articles" ) var relatedArticles : ArrayList<RelatedArticles> = arrayListOf()

)