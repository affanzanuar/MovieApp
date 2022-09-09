package com.affan.movieapp.view.main.series

import com.affan.movieapp.data.Data
import com.affan.movieapp.view.main.series.presenter.SeriesPresenter
import com.affan.movieapp.view.main.series.presenter.SeriesView

class SeriesPresenterImpl (private val seriesView: SeriesView) : SeriesPresenter{
    override fun getMovies() {
        seriesView.onReceiveSeries(
            Data.itemSeries
        )
    }

}