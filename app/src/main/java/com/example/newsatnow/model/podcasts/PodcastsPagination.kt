package com.example.example

import com.google.gson.annotations.SerializedName


data class PodcastsPagination (

  @SerializedName("current_page"      ) var currentPage     : Int?     = null,
  @SerializedName("total_pages"       ) var totalPages      : Int?     = null,
  @SerializedName("per_page"          ) var perPage         : Int?     = null,
  @SerializedName("total_count"       ) var totalCount      : Int?     = null,
  @SerializedName("has_next_page"     ) var hasNextPage     : Boolean? = null,
  @SerializedName("has_previous_page" ) var hasPreviousPage : Boolean? = null

)