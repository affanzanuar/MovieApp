package com.affan.movieapp.view.main.movies.presenter

import com.affan.movieapp.view.main.movies.MoviesData

interface MoviesView {
    fun onReceiveMovies(movies: List<MoviesData>)
}