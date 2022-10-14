package com.affan.movieapp.data.local

import com.affan.movieapp.data.DataSource
import com.affan.movieapp.data.local.room.MovieDatabase
import com.affan.movieapp.model.favorite.FavoriteMovies
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Response

class LocalDataSource(
    private val moviesDatabase: MovieDatabase,
) : DataSource {
    override suspend fun getTopMoviesOrSeries(apiKey: String): TrendingResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getNowPlaying(apiKey: String): MovieResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getMostPopularMovie(apiKey: String, page : Int): MovieResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getMostPopularSeries(apiKey: String, page : Int): SeriesResponse {
        throw UnsupportedOperationException("Use Remote Data Source!")
    }

    override suspend fun getComingSoon(
        apiKey: String,
        language: String,
        sortBy: String,
        page: Int,
        releaseDateGte: String,
        releaseDateLte: String,
        monetizationTypes: String
    ): ComingSoonResponse {
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

    override suspend fun getFavorite(): List<FavoriteMovies> {
        return moviesDatabase.moviesDao().getAllFavorite()
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