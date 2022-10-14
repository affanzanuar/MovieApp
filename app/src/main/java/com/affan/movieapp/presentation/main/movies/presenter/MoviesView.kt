package com.affan.movieapp.presentation.main.movies.presenter

import com.affan.movieapp.data.model.movie.Movie

@Deprecated("unused anymore")
interface MoviesView {
    fun onReceiveMovies(movies: List<Movie>)
    fun onSuccessGetPopularMovies(movies: List<Movie?>)
    fun onFailGetPopularMovies(string: String)
}