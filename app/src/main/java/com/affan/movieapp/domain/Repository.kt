package com.affan.movieapp.domain

import com.affan.movieapp.model.favorite.FavoriteMovies
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Response

interface Repository {
    suspend fun getTopMoviesOrSeries(
        apiKey: String
    ) : TrendingResponse

    suspend fun getNowPlaying(
        apiKey: String
    ) : MovieResponse

    suspend fun getMostPopularMovie(
        apiKey: String,
        page : Int
    ) : MovieResponse

    suspend fun getMostPopularSeries(
        apiKey: String,
        page : Int
    ) : SeriesResponse

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

    suspend fun getPopularSeries(
        page: Int,
        apiKey: String
    ): Response<SeriesResponse>

    suspend fun getPopularMovies(
        page: Int,
        apiKey: String
    ): Response<MovieResponse>
}