package com.affan.movieapp.main.home.domain

import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Call

interface Repository {
    fun getTopMoviesOrSeries(apiKey: String,) : Call<TrendingResponse>
    fun getNowPlaying(apiKey: String,) : Call<MovieResponse>
    fun getMostPopularMovie(apiKey: String,) : Call<MovieResponse>
    fun getMostPopularSeries(apiKey: String,) : Call<SeriesResponse>
    fun getComingSoon(
        apiKey: String,
        language : String,
        sortBy : String,
        page : Int,
        releaseDateGte : String,
        releaseDateLte : String,
        monetizationTypes : String,
    ) : Call<ComingSoonResponse>
}