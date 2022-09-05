package com.affan.movieapp.view.main.home.presenter

import com.affan.movieapp.model.MoviesOrSeries

interface HomeView {
    fun onReceiveTopMoviesOrSeries(moviesOrSeries: List<MoviesOrSeries>)
    fun onReceiveHorizontalList(moviesOrSeries: List<MoviesOrSeries>)
}