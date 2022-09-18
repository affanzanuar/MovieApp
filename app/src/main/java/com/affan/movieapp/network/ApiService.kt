package com.affan.movieapp.network

import com.affan.movieapp.model.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getNowPlaying(
        @Query("api_key") apiKey : String
    ) : Call<MovieResponse>

}