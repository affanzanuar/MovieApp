package com.affan.movieapp.view.main.account.myfavorite.presenter

import com.affan.movieapp.model.MoviesOrSeries

interface FavoriteView {
    fun onGetDataFavoriteSuccess(data : List<MoviesOrSeries>)
    fun onGetDataFavoriteFailure(message : String)
}