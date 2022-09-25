package com.affan.movieapp.main.home

import com.affan.movieapp.main.home.domain.Repository
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import com.affan.movieapp.network.ApiService
import retrofit2.Call

class RepositoryImp (
    private val apiService: ApiService
        ) : Repository {
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
        language : String,
        sortBy : String,
        page : Int,
        releaseDateGte : String,
        releaseDateLte : String,
        monetizationTypes : String,
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
}