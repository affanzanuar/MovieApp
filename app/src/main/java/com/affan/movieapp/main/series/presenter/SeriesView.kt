package com.affan.movieapp.main.series.presenter

import com.affan.movieapp.model.series.Series
import com.affan.movieapp.main.series.SeriesData

interface SeriesView {
    fun onReceiveSeries(series: List<SeriesData>)
    fun onSuccessGetPopularSeries(series: List<Series?>)
    fun onFailGetPopularSeries(string: String)
}