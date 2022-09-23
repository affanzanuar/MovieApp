package com.affan.movieapp.main.movies

import com.affan.movieapp.data.Data
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.network.ApiClient
import retrofit2.Response

class MoviesRepository {
    suspend fun getPopularMovies(): Response<MovieResponse> {
        return ApiClient.instance.getMostPopularMovies2(Data.apiKey)
    }
}