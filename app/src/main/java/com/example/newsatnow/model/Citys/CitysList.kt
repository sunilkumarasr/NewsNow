package com.example.newsatnow.model.Citys

import com.google.gson.annotations.SerializedName

data class CitysList(
    @SerializedName("id"          ) var id         : Int?      = null,
    @SerializedName("name"       ) var name      : String?   = null,
)