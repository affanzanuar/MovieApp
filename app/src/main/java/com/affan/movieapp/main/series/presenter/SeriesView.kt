package com.affan.movieapp.main.series.presenter

import com.affan.movieapp.data.model.series.Series

@Deprecated("unused anymore")
interface SeriesView {
    fun onReceiveSeries(series: List<Series>)
    fun onSuccessGetPopularSeries(series: List<Series?>)
    fun onFailGetPopularSeries(string: String)
}