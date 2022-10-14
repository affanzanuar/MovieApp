package com.affan.movieapp.presentation.main.series.presenter

import kotlinx.coroutines.CoroutineScope

@Deprecated("Moved to MVVM")
class SeriesPresenterImpl(
    private val seriesView: SeriesView,
    private val coroutineScope: CoroutineScope
) : SeriesPresenter {
    override fun getPopularSeries() {
//        coroutineScope.launch {
//            withContext(Dispatchers.IO) {
//                ApiClient.instance.getMostPopularSeries(Data.apiKey)
//                    .enqueue(object : Callback<SeriesResponse> {
//                        override fun onResponse(
//                            call: Call<SeriesResponse>,
//                            response: Response<SeriesResponse>
//                        ) {
//                            val body = response.body()!!
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main) {
//                                    body.series
//                                        .let {
//                                            if (it != null) {
//                                                seriesView.onSuccessGetPopularSeries(it)
//                                                Log.d(
//                                                    "Main Presenter adalah",
//                                                    response.body()?.series.toString()
//                                                )
//                                            }
//                                        }
//                                }
//                            }
//                        }
//
//                        override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
//                            coroutineScope.launch {
//                                withContext(Dispatchers.Main) {
//                                    seriesView.onFailGetPopularSeries(t.message!!)
//                                }
//                            }
//                        }
//                    })
//            }
//        }
    }

}