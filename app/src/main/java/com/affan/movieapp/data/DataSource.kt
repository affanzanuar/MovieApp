package com.affan.movieapp.data

import com.affan.movieapp.model.FavoriteMovies
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Call

interface DataSource {
    suspend fun getTopMoviesOrSeries(apiKey: String) : TrendingResponse
    suspend fun getNowPlaying(apiKey: String) : MovieResponse
    suspend fun getMostPopularMovie(apiKey: String) : MovieResponse
    suspend fun getMostPopularSeries(apiKey: String) : SeriesResponse
    suspend fun getComingSoon(
        apiKey: String,
        language : String,
        sortBy : String,
        page : Int,
        releaseDateGte : String,
        releaseDateLte : String,
        monetizationTypes : String,
    ) : ComingSoonResponse

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
    ) : List<FavoriteMovies>

    suspend fun insertFavorite(
        favoriteMovies: FavoriteMovies
    )

    suspend fun deleteFavorite(
        favoriteMovies: FavoriteMovies
    )

    suspend fun getMostPopularSeries3(
        apiKey : String,
        page: Int
    ) : retrofit2.Response<SeriesResponse>

    suspend fun getMostPopularMovies3(
        apiKey : String,
        page: Int
    ) : retrofit2.Response<MovieResponse>
}