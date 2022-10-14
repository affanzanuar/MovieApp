package com.affan.movieapp.domain

import com.affan.movieapp.data.model.favorite.FavoriteMovies
import com.affan.movieapp.data.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.data.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.data.model.details.videos.VideosResponse
import com.affan.movieapp.data.model.movie.MovieResponse
import com.affan.movieapp.data.model.series.SeriesResponse
import com.affan.movieapp.data.model.trending.TrendingResponse

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

}