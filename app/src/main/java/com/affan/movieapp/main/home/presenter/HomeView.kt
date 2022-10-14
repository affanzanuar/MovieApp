package com.affan.movieapp.main.home.presenter

import com.affan.movieapp.data.model.comingsoon.ComingSoon
import com.affan.movieapp.data.model.movie.Movie
import com.affan.movieapp.data.model.series.Series
import com.affan.movieapp.data.model.trending.Trending

@Deprecated("not used anymore")
interface HomeView {
    fun onSuccessReceiveTopMoviesOrSeries(moviesOrSeries: List<Trending?>)
    fun onFailureReceiveTopMoviesOrSeries(message: String)

    fun onSuccessGetInTheater(moviesOrSeries : List<Movie?>)
    fun onFailureGetInTheater(message : String)

    fun onSuccessGetPopularMovie(moviesOrSeries : List<Movie?>)
    fun onFailureGetPopularMovie(message : String)

    fun onSuccessGetPopularSeries(moviesOrSeries : List<Series?>)
    fun onFailureGetPopularSeries(message : String)

    fun onSuccessGetComingSoon(moviesOrSeries : List<ComingSoon?>)
    fun onFailureGetComingSoon(message : String)
}