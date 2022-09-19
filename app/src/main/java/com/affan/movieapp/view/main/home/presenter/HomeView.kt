package com.affan.movieapp.view.main.home.presenter

import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.trending.MoviesSeries

interface HomeView {
    fun onSuccessReceiveTopMoviesOrSeries(moviesOrSeries: List<MoviesSeries?>)
    fun onFailureReceiveTopMoviesOrSeries(message: String)
    fun onSuccessGetInTheater(moviesOrSeries : List<Movie?>)
    fun onFailureGetInTheater(message : String)
    fun onSuccessGetPopularMovie(moviesOrSeries : List<Movie?>)
    fun onFailureGetPopularMovie(message : String)
}