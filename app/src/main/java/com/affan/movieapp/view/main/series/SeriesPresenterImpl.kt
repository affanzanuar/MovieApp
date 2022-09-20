package com.affan.movieapp.view.main.series

import android.util.Log
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.network.ApiClient
import com.affan.movieapp.view.main.series.network.SeriesApiClient
import com.affan.movieapp.view.main.series.presenter.SeriesPresenter
import com.affan.movieapp.view.main.series.presenter.SeriesView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesPresenterImpl(
    private val seriesView: SeriesView,
    private val coroutineScope: CoroutineScope
) : SeriesPresenter {
    override fun getPopularSeries() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                SeriesApiClient.instance.getMostPopularSeries(Data.apiKey)
                    .enqueue(object : Callback<SeriesResponse> {
                        override fun onResponse(
                            call: Call<SeriesResponse>,
                            response: Response<SeriesResponse>
                        ) {
                            val body = response.body()!!
                            coroutineScope.launch {
                                withContext(Dispatchers.Main) {
                                    body.series
                                        .let {
                                            if (it != null) {
                                                seriesView.onSuccessGetPopularSeries(it)
                                                Log.d(
                                                    "Main Presenter adalah",
                                                    response.body()?.series.toString()
                                                )
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                            coroutineScope.launch {
                                withContext(Dispatchers.Main) {
                                    seriesView.onFailGetPopularSeries(t.message!!)
                                }
                            }
                        }
                    })
            }
        }
    }

}