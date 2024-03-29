package com.affan.movieapp.data.remote.network

import com.affan.movieapp.data.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.data.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.data.model.details.videos.VideosResponse
import com.affan.movieapp.data.model.movie.MovieResponse
import com.affan.movieapp.data.model.series.SeriesResponse
import com.affan.movieapp.data.model.trending.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("trending/all/day")
    suspend fun getTopMoviesOrSeries(
        @Query("api_key") apiKey: String
    ) : TrendingResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey : String
    ) : MovieResponse

    @GET("movie/popular")
    suspend fun getMostPopularMovie(
        @Query("api_key") apiKey : String,
        @Query("page") page: Int
    ) : MovieResponse

    @GET("tv/popular")
    suspend fun getMostPopularSeries(
        @Query("api_key") apiKey : String,
        @Query("page") page: Int
    ) : SeriesResponse

    @GET("discover/movie")
    suspend fun getComingSoon(
        @Query("api_key") apiKey: String,
        @Query("language") language : String,
        @Query("sort_by") sortBy : String,
        @Query("page") page : Int,
        @Query("primary_release_date.gte") releaseDateGte : String,
        @Query("primary_release_date.lte") releaseDateLte : String,
        @Query("with_watch_monetization_types") monetizationTypes : String,
    ) : ComingSoonResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id:Int,
        @Query("api_key") apiKey: String,
    ): DetailsMovieResponse

    @GET("tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") id:Int,
        @Query("api_key") apiKey: String,
    ): DetailsMovieResponse


    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") id:Int,
        @Query("api_key") apiKey: String,
    ): VideosResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getTvVideos(
        @Path("tv_id") id:Int,
        @Query("api_key") apiKey: String,
    ): VideosResponse

}