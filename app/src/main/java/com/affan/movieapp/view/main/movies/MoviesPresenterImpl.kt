package com.affan.movieapp.view.main.movies

import com.affan.movieapp.data.Data

class MoviesPresenterImpl (private val moviesView: MoviesView) : MoviesPresenter{
    override fun getMovies() {
        moviesView.onReceiveMovies(
            Data.itemMovies
        )
    }

}