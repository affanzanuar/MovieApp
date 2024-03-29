package com.affan.movieapp.data.model.details.videos


import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<VideosResult?>?
)