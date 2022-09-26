package com.affan.movieapp.main.account.myfavorite

import com.affan.movieapp.data.Data
import com.affan.movieapp.main.account.myfavorite.presenter.FavoritePresenter
import com.affan.movieapp.main.account.myfavorite.presenter.FavoriteView

@Deprecated("Not Used Anymore")
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