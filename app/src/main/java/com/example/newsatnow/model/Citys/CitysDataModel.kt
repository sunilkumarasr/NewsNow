package com.example.newsatnow.model.Citys

import com.example.example.TrendingData
import com.google.gson.annotations.SerializedName

data class CitysDataModel(
    @SerializedName("success" ) var success : String? = null,
    @SerializedName("data" ) var data : ArrayList<CitysList> = arrayListOf(),
    @SerializedName("message" ) var message : String?  = null
)
