package com.affan.movieapp.main.series

import android.util.Log
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.series.SeriesResponse
import com.affan.movieapp.data.remote.network.ApiClient
import com.affan.movieapp.main.series.presenter.SeriesPresenter
import com.affan.movieapp.main.series.presenter.SeriesView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Deprecated("Moved to MVVM")
class SeriesPresenterImpl(
    private val seriesView: SeriesView,
    private val coroutineScope: CoroutineScope
) : SeriesPresenter {
    override fun getPopularSeries() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                ApiClient.instance.getMostPopularSeries(Data.apiKey)
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