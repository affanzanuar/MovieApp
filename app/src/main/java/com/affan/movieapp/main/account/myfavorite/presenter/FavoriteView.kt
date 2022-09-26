package com.affan.movieapp.main.account.myfavorite.presenter

import com.affan.movieapp.model.MoviesOrSeries

@Deprecated("Not Used Anymore")
interface FavoriteView {
    fun onGetDataFavoriteSuccess(data : List<MoviesOrSeries>)
    fun onGetDataFavoriteFailure(message : String)
}