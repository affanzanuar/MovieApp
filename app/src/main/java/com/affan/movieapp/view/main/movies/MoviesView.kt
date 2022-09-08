package com.affan.movieapp.view.main.movies

interface MoviesView {
    fun onReceiveMovies(movies: List<MoviesData>)
}