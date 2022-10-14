package com.affan.movieapp.data.remote

import com.affan.movieapp.data.DataSource
import com.affan.movieapp.model.favorite.FavoriteMovies
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import com.affan.movieapp.data.remote.network.ApiService

class RemoteDataSource (
    private val apiService: ApiService
        ) : DataSource {

    override suspend fun getTopMoviesOrSeries(apiKey: String): TrendingResponse {
        return apiService.getTopMoviesOrSeries(apiKey)
    }

    override suspend fun getNowPlaying(apiKey: String): MovieResponse {
        return apiService.getNowPlaying(apiKey)
    }

    override suspend fun getMostPopularMovie(apiKey: String, page : Int): MovieResponse {
        return apiService.getMostPopularMovie(apiKey, page)
    }

    override suspend fun getMostPopularSeries(apiKey: String, page : Int): SeriesResponse {
        return apiService.getMostPopularSeries(apiKey, page)
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

}