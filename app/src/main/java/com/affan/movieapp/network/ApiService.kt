package com.affan.movieapp.network

import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending/all/day")
    fun getTopMoviesOrSeries(
        @Query("api_key") apiKey: String
    ) : Call<TrendingResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey : String
    ) : Call<MovieResponse>

    @GET("movie/popular")
    fun getMostPopularMovie(
        @Query("api_key") apiKey : String
    ) : Call<MovieResponse>

    @GET("tv/popular")
    fun getMostPopularSeries(
        @Query("api_key") apiKey : String
    ) : Call<MovieResponse>

    @GET("discover/movie")
    fun getComingSoon(
        @Query("api_key") apiKey: String,
        @Query("language") language : String,
        @Query("sort_by") sortBy : String,
        @Query("release_date.gte") releaseDateGte : String,
        @Query("release_date.lte") releaseDateLte : String,
    ) : Call<ComingSoonResponse>



}