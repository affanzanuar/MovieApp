package com.affan.movieapp.view.main.home.presenter

import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.trending.MoviesSeries

interface HomeView {
    fun onSuccessReceiveTopMoviesOrSeries(moviesOrSeries: List<MoviesSeries?>)
    fun onFailureReceiveTopMoviesOrSeries(message: String)
    fun onSuccessReceiveHorizontalList(moviesOrSeries : List<Movie?>)
    fun onFailureReceiveHorizontalList(message : String)
}