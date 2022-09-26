package com.affan.movieapp.domain

import androidx.lifecycle.LiveData
import com.affan.movieapp.data.local.room.Favorite
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Call
import retrofit2.Response

interface Repository {
    fun getTopMoviesOrSeries(apiKey: String) : Call<TrendingResponse>
    fun getNowPlaying(apiKey: String) : Call<MovieResponse>
    fun getMostPopularMovie(apiKey: String) : Call<MovieResponse>
    fun getMostPopularSeries(apiKey: String) : Call<SeriesResponse>
    fun getComingSoon(
        apiKey: String,
        language : String,
        sortBy : String,
        page : Int,
        releaseDateGte : String,
        releaseDateLte : String,
        monetizationTypes : String,
    ) : Call<ComingSoonResponse>

    suspend fun getMovieDetails(
        id:Int,
        apiKey: String,
    ): DetailsMovieResponse

    suspend fun getTvDetails(
        id:Int,
        apiKey: String,
    ): DetailsMovieResponse

    suspend fun getMovieVideos(
        id:Int,
        apiKey: String,
    ): VideosResponse

    suspend fun getTvVideos(
        id:Int,
        apiKey: String,
    ): VideosResponse

    suspend fun getFavorite(
        id: Int
    ) : LiveData<List<Favorite>>

    suspend fun insertFavorite(
        id: Int
    ) : Favorite

    suspend fun deleteFavorite(
        id: Int
    ) : Favorite

    suspend fun getPopularSeries(page: Int, apiKey: String): Response<SeriesResponse>

    suspend fun getPopularMovies(page: Int, apiKey: String): Response<MovieResponse>
}