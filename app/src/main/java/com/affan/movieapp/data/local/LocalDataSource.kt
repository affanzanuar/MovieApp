package com.affan.movieapp.data.local

import com.affan.movieapp.data.DataSource
import com.affan.movieapp.data.local.room.MovieDatabase
import com.affan.movieapp.data.local.room.FavoriteMovies
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Call
import retrofit2.Response

class LocalDataSource(
    private val moviesDatabase: MovieDatabase,
) : DataSource {
    override fun getTopMoviesOrSeries(apiKey: String): Call<TrendingResponse> {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override fun getNowPlaying(apiKey: String): Call<MovieResponse> {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override fun getMostPopularMovie(apiKey: String): Call<MovieResponse> {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override fun getMostPopularSeries(apiKey: String): Call<SeriesResponse> {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override fun getComingSoon(
        apiKey: String,
        language: String,
        sortBy: String,
        page: Int,
        releaseDateGte: String,
        releaseDateLte: String,
        monetizationTypes: String
    ): Call<ComingSoonResponse> {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getMovieDetails(id: Int, apiKey: String): DetailsMovieResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getTvDetails(id: Int, apiKey: String): DetailsMovieResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getMovieVideos(id: Int, apiKey: String): VideosResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getTvVideos(id: Int, apiKey: String): VideosResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getFavorite(id: Int): List<FavoriteMovies> {
        return moviesDatabase.moviesDao().getAllFavorit()
    }

    override suspend fun insertFavorite(favoriteMovies: FavoriteMovies) {
        moviesDatabase.moviesDao().insertFavorite(favoriteMovies)
    }

    override suspend fun deleteFavorite(favoriteMovies: FavoriteMovies) {
        moviesDatabase.moviesDao().deleteFavorite(favoriteMovies)
    }

    override suspend fun getMostPopularSeries3(
        apiKey: String,
        page: Int
    ): Response<SeriesResponse> {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getMostPopularMovies3(apiKey: String, page: Int): Response<MovieResponse> {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }
}