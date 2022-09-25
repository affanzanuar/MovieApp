package com.affan.movieapp.domain

import com.affan.movieapp.data.Data
import com.affan.movieapp.data.DataSource
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import com.affan.movieapp.network.ApiClient
import com.affan.movieapp.network.ApiService
import retrofit2.Call
import retrofit2.Response

class RepositoryImp (
//    private val localDataSource: DataSource,
    private val remoteDataSource : DataSource
        ) : Repository {
    override fun getTopMoviesOrSeries(apiKey: String): Call<TrendingResponse> {
        return remoteDataSource.getTopMoviesOrSeries(apiKey)
    }

    override fun getNowPlaying(apiKey: String): Call<MovieResponse> {
        return remoteDataSource.getNowPlaying(apiKey)
    }

    override fun getMostPopularMovie(apiKey: String): Call<MovieResponse> {
        return remoteDataSource.getMostPopularMovie(apiKey)
    }

    override fun getMostPopularSeries(apiKey: String): Call<SeriesResponse> {
        return remoteDataSource.getMostPopularSeries(apiKey)
    }

    override fun getComingSoon(
        apiKey: String,
        language : String,
        sortBy : String,
        page : Int,
        releaseDateGte : String,
        releaseDateLte : String,
        monetizationTypes : String,
    ): Call<ComingSoonResponse> {
        return remoteDataSource.getComingSoon(
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
        return remoteDataSource.getMovieDetails(id,apiKey)
    }

    override suspend fun getTvDetails(id: Int, apiKey: String): DetailsMovieResponse {
        return remoteDataSource.getTvDetails(id,apiKey)
    }

    override suspend fun getMovieVideos(id: Int, apiKey: String): VideosResponse {
        return remoteDataSource.getMovieVideos(id,apiKey)
    }

    override suspend fun getTvVideos(id: Int, apiKey: String): VideosResponse {
        return remoteDataSource.getTvVideos(id,apiKey)
    }

    override suspend fun getFavorite(id: Int): List<MoviesOrSeries> {
        throw UnsupportedOperationException("ereor")
    }

    override suspend fun deleteFavorite(id: Int): MoviesOrSeries {
        throw UnsupportedOperationException("ereor")
    }

    override suspend fun getPopularSeries(page: Int): Response<SeriesResponse> {
        return ApiClient.instance.getMostPopularSeries3(Data.apiKey, page)
    }
}