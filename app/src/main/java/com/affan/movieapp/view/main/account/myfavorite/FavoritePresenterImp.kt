package com.affan.movieapp.view.main.account.myfavorite

import com.affan.movieapp.data.Data
import com.affan.movieapp.view.main.account.myfavorite.presenter.FavoritePresenter
import com.affan.movieapp.view.main.account.myfavorite.presenter.FavoriteView

class FavoritePresenterImp(private val favoriteView: FavoriteView) : FavoritePresenter{
    override fun getContentListFavorite() {
        if (Data.itemTopMovies.isNotEmpty()){
            favoriteView.onGetDataFavoriteSuccess(
                Data.itemTopMovies
            )
        } else {
            favoriteView.onGetDataFavoriteFailure(
                "Data tidak ditemukan"
            )
        }
    }
}