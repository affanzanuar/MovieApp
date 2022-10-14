package com.affan.movieapp.data.model.comingsoon


import com.google.gson.annotations.SerializedName

data class ComingSoonResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ComingSoon?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)