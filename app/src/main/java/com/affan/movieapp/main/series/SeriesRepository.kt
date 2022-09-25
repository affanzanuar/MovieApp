package com.affan.movieapp.main.series

import com.affan.movieapp.data.Data
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.network.ApiClient
import retrofit2.Response

class SeriesRepository {
    suspend fun getPopularSeries(page: Int): Response<SeriesResponse> {
        return ApiClient.instance.getMostPopularSeries3(Data.apiKey, page)
    }
}