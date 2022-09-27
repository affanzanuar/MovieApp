package com.affan.movieapp.data.remote

import com.affan.movieapp.data.DataSource
import com.affan.movieapp.data.local.room.FavoriteMovies
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import com.affan.movieapp.data.remote.network.ApiService
import retrofit2.Call
import retrofit2.Response

class RemoteDataSource (
    private val apiService: ApiService
        ) : DataSource {
    override fun getTopMoviesOrSeries(apiKey: String): Call<TrendingResponse> {
        return apiService.getTopMoviesOrSeries(apiKey)
    }

    override fun getNowPlaying(apiKey: String): Call<MovieResponse> {
        return apiService.getNowPlaying(apiKey)
    }

    override fun getMostPopularMovie(apiKey: String): Call<MovieResponse> {
        return apiService.getMostPopularMovie(apiKey)
    }

    override fun getMostPopularSeries(apiKey: String): Call<SeriesResponse> {
        return apiService.getMostPopularSeries(apiKey)
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
        return apiService.getComingSoon(
            apiKey,
            language,
            sortBy,
            page,
            releaseDateGte,
            releaseDateLte,
            monetizationTypes
        )
    }

    override suspend fun getMovieDetails(id: Int, apiKey: String): DetailsMovieResponse {
        return apiService.getMovieDetails(id,apiKey)
    }

    override suspend fun getTvDetails(id: Int, apiKey: String): DetailsMovieResponse {
        return apiService.getTvDetails(id,apiKey)
    }

    override suspend fun getMovieVideos(id: Int, apiKey: String): VideosResponse {
        return apiService.getMovieVideos(id,apiKey)
    }

    override suspend fun getTvVideos(id: Int, apiKey: String): VideosResponse {
        return apiService.getTvVideos(id,apiKey)
    }

    override suspend fun getFavorite(): List<FavoriteMovies> {
        throw UnsupportedOperationException("Use Local Data Source!")
    }

    override suspend fun insertFavorite(favoriteMovies: FavoriteMovies) {
        throw UnsupportedOperationException("Use Local Data Source!")
    }

    override suspend fun deleteFavorite(favoriteMovies: FavoriteMovies) {
        throw UnsupportedOperationException("Use Local Data Source!")
    }

    override suspend fun getMostPopularSeries3(
        apiKey: String,
        page: Int
    ): Response<SeriesResponse> {
        return apiService.getMostPopularSeries3(apiKey, page)
    }

    override suspend fun getMostPopularMovies3(apiKey: String, page: Int): Response<MovieResponse> {
        return apiService.getMostPopularMovies3(apiKey, page)
    }
}