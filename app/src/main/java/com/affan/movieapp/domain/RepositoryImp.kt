package com.affan.movieapp.domain

import androidx.lifecycle.LiveData
import com.affan.movieapp.data.DataSource
import com.affan.movieapp.data.DataSourceFactory
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

class RepositoryImp @Inject constructor (
    private val dataSourceFactory: DataSourceFactory
        ) : Repository {
    override fun getTopMoviesOrSeries(apiKey: String): Call<TrendingResponse> {
        return dataSourceFactory.getTopMoviesOrSeries(apiKey)
    }

    override fun getNowPlaying(apiKey: String): Call<MovieResponse> {
        return dataSourceFactory.getNowPlaying(apiKey)
    }

    override fun getMostPopularMovie(apiKey: String): Call<MovieResponse> {
        return dataSourceFactory.getMostPopularMovie(apiKey)
    }

    override fun getMostPopularSeries(apiKey: String): Call<SeriesResponse> {
        return dataSourceFactory.getMostPopularSeries(apiKey)
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
        return dataSourceFactory.getComingSoon(
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
        return dataSourceFactory.getMovieDetails(id,apiKey)
    }

    override suspend fun getTvDetails(id: Int, apiKey: String): DetailsMovieResponse {
        return dataSourceFactory.getTvDetails(id,apiKey)
    }

    override suspend fun getMovieVideos(id: Int, apiKey: String): VideosResponse {
        return dataSourceFactory.getMovieVideos(id,apiKey)
    }

    override suspend fun getTvVideos(id: Int, apiKey: String): VideosResponse {
        return dataSourceFactory.getTvVideos(id,apiKey)
    }

    override suspend fun getFavorite(id: Int): LiveData<List<Favorite>> {
        return dataSourceFactory.getFavorite(id)
    }

    override suspend fun insertFavorite(id: Int): Favorite {
        return dataSourceFactory.insertFavorite(id)
    }

    override suspend fun deleteFavorite(id: Int): Favorite {
        return dataSourceFactory.deleteFavorite(id)
    }

    override suspend fun getPopularSeries(page: Int, apiKey: String): Response<SeriesResponse> {
        return dataSourceFactory.getMostPopularSeries3(apiKey, page)
    }

    override suspend fun getPopularMovies(page: Int, apiKey: String): Response<MovieResponse> {
        return dataSourceFactory.getMostPopularMovies3(apiKey, page)
    }
}