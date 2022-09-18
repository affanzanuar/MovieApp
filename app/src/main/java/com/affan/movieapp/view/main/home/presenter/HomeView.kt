package com.affan.movieapp.view.main.home.presenter

import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.model.movie.Movie

interface HomeView {
    fun onSuccessReceiveTopMoviesOrSeries(moviesOrSeries: List<MoviesOrSeries>)
    fun onFailureReceiveTopMoviesOrSeries(message: String)
    fun onSuccessReceiveHorizontalList(moviesOrSeries : List<Movie?>)
    fun onFailureReceiveHorizontalList(message : String)
}