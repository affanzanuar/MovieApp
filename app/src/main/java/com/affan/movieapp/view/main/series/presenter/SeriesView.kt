package com.affan.movieapp.view.main.series.presenter

import com.affan.movieapp.view.main.series.SeriesData

interface SeriesView {
    fun onReceiveSeries(series: List<SeriesData>)
}