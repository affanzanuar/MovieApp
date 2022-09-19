package com.affan.movieapp.model.trending


import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MoviesSeries?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)