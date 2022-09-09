package com.affan.movieapp.view.main.movies

import com.affan.movieapp.data.Data
import com.affan.movieapp.view.main.movies.presenter.MoviesPresenter
import com.affan.movieapp.view.main.movies.presenter.MoviesView

class MoviesPresenterImpl (private val moviesView: MoviesView) : MoviesPresenter {
    override fun getMovies() {
        moviesView.onReceiveMovies(
            Data.itemMovies
        )
    }

}