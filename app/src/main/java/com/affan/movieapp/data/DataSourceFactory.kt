package com.affan.movieapp.data

import androidx.lifecycle.LiveData
import com.affan.movieapp.data.local.LocalDataSource
import com.affan.movieapp.data.local.room.Favorite
import com.affan.movieapp.data.remote.RemoteDataSource
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class DataSourceFactory @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DataSource {
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
        language: String,
        sortBy: String,
        page: Int,
        releaseDateGte: String,
        releaseDateLte: String,
        monetizationTypes: String
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

    override suspend fun getFavorite(id: Int): LiveData<List<Favorite>> {
        return localDataSource.getFavorite(id)
    }

    override suspend fun insertFavorite(id: Int): Favorite {
        return localDataSource.insertFavorite(id)
    }

    override suspend fun deleteFavorite(id: Int): Favorite {
        return localDataSource.deleteFavorite(id)
    }

    override suspend fun getMostPopularSeries3(
        apiKey: String,
        page: Int
    ): Response<SeriesResponse> {
        return remoteDataSource.getMostPopularSeries3(apiKey, page)
    }

    override suspend fun getMostPopularMovies3(apiKey: String, page: Int): Response<MovieResponse> {
        return remoteDataSource.getMostPopularMovies3(apiKey, page)
    }

}