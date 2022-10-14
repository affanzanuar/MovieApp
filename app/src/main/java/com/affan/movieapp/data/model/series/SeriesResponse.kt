package com.affan.movieapp.data.model.series


import com.google.gson.annotations.SerializedName


data class SeriesResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val series: MutableList<Series?>,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)