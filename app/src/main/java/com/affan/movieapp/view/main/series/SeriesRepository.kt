package com.affan.movieapp.view.main.series

import com.affan.movieapp.data.Data
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class SeriesRepository {
    suspend fun getPopularSeries(): Call<SeriesResponse> {
        return ApiClient.instance.getMostPopularSeries2(Data.apiKey)
    }
}