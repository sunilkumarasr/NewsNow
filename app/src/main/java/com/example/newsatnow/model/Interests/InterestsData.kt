package com.example.example

import com.google.gson.annotations.SerializedName


data class InterestsData (

  @SerializedName("recommended"      ) var recommended      : ArrayList<InterestsCategories>      = arrayListOf(),
  @SerializedName("all"          ) var all          : ArrayList<InterestsCategories>      = arrayListOf(),
  @SerializedName("message"         ) var message         : String?                    = null

)