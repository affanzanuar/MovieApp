package com.affan.movieapp.network

import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getNowPlaying(
        @Query("api_key") apiKey : String
    ) : Call<MovieResponse>

    @GET("trending/all/day")
    fun getTopMoviesOrSeries(
        @Query("api_key") apiKey: String
    ) : Call<TrendingResponse>

}