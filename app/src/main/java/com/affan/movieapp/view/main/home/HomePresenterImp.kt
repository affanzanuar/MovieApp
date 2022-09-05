package com.affan.movieapp.view.main.home

import com.affan.movieapp.data.Data
import com.affan.movieapp.view.main.home.presenter.HomePresenter
import com.affan.movieapp.view.main.home.presenter.HomeView

class HomePresenterImp(private val homeView: HomeView) : HomePresenter {

    override fun getTopMoviesOrSeries(){
        homeView.onReceiveTopMoviesOrSeries(
            Data.itemTopMovies
        )
    }

    override fun getInTheaters(){
        homeView.onReceiveHorizontalList(
            Data.itemInTheaters
        )
    }

    override fun getMostPopularMovies(){
        homeView.onReceiveHorizontalList(
            Data.itemMostPopularMovies
        )
    }

    override fun getMostPopularSeries(){
        homeView.onReceiveHorizontalList(
            Data.itemMostPopularSeries
        )
    }

    override fun getComingSoon(){
        homeView.onReceiveHorizontalList(
            Data.itemComingSoon
        )
    }
}