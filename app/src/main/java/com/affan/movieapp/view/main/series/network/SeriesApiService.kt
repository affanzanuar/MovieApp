package com.affan.movieapp.view.main.series.network

import com.affan.movieapp.view.main.series.SeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesApiService {
    @GET("tv/popular")
    fun getMostPopularSeries(
        @Query("api_key") apiKey : String
    ) : Call<SeriesResponse>
}