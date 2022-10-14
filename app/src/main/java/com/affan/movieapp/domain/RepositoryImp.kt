package com.affan.movieapp.domain

import com.affan.movieapp.data.DataSource
import com.affan.movieapp.model.favorite.FavoriteMovies
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.details.movies.DetailsMovieResponse
import com.affan.movieapp.model.details.videos.VideosResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.model.trending.TrendingResponse
import retrofit2.Response

class RepositoryImp (
    private val localDataSource: DataSource,
    private val remoteDataSource : DataSource
        ) : Repository {

    override suspend fun getTopMoviesOrSeries(apiKey: String): TrendingResponse {
        return remoteDataSource.getTopMoviesOrSeries(apiKey)
    }

    override suspend fun getNowPlaying(apiKey: String): MovieResponse {
        return remoteDataSource.getNowPlaying(apiKey)
    }

    override suspend fun getMostPopularMovie(apiKey: String, page : Int): MovieResponse {
        return remoteDataSource.getMostPopularMovie(apiKey,page)
    }

    override suspend fun getMostPopularSeries(apiKey: String, page : Int): SeriesResponse {
        return remoteDataSource.getMostPopularSeries(apiKey, page)
    }

    override suspend fun getComingSoon(
        apiKey: String,
        language : String,
        sortBy : String,
        page : Int,
        releaseDateGte : String,
        releaseDateLte : String,
        monetizationTypes : String,
    ): ComingSoonResponse {
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

    override suspend fun getFavorite(): List<FavoriteMovies> {
        return localDataSource.getFavorite()
    }

    override suspend fun insertFavorite(favoriteMovies: FavoriteMovies) {
        return localDataSource.insertFavorite(favoriteMovies)
    }

    override suspend fun deleteFavorite(favoriteMovies: FavoriteMovies) {
        return localDataSource.deleteFavorite(favoriteMovies)
    }

}