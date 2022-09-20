package com.affan.movieapp.view.main.home

import android.util.Log
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.comingsoon.ComingSoonResponse
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.trending.TrendingResponse
import com.affan.movieapp.network.ApiClient
import com.affan.movieapp.view.main.home.presenter.HomePresenter
import com.affan.movieapp.view.main.home.presenter.HomeView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenterImp(
    private val homeView: HomeView,
    private val coroutineScope : CoroutineScope
    ) : HomePresenter {

    override fun getTopMoviesOrSeries(){
        coroutineScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getTopMoviesOrSeries(Data.apiKey)
                    .enqueue(object : Callback<TrendingResponse> {
                        override fun onResponse(
                            call: Call<TrendingResponse>,
                            response: Response<TrendingResponse>
                        ) {
                            val body = response.body()!!
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                homeView.onSuccessReceiveTopMoviesOrSeries(it)
                                                Log.d("Main Presenter adalah",
                                                    response.body()?.results.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    homeView.onFailureReceiveTopMoviesOrSeries(t.message!!)
                                }
                            }
                        }
                    })
            }
        }
    }

    override fun getInTheaters(){
        coroutineScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getNowPlaying(Data.apiKey)
                    .enqueue(object : Callback<MovieResponse> {
                        override fun onResponse(
                            call: Call<MovieResponse>,
                            response: Response<MovieResponse>
                        ) {
                            val body = response.body()!!
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                homeView.onSuccessGetInTheater(it)
                                                Log.d("Main Presenter adalah",
                                                    response.body()?.results.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    homeView.onFailureGetInTheater(t.message!!)
                                }
                            }
                        }
                    })
            }
        }
    }

    override fun getMostPopularMovies(){
        coroutineScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getMostPopularMovie(Data.apiKey)
                    .enqueue(object : Callback<MovieResponse> {
                        override fun onResponse(
                            call: Call<MovieResponse>,
                            response: Response<MovieResponse>
                        ) {
                            val body = response.body()!!
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                homeView.onSuccessGetPopularMovie(it)
                                                Log.d("Main Presenter adalah",
                                                    response.body()?.results.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    homeView.onFailureGetPopularMovie(t.message!!)
                                }
                            }
                        }
                    })
            }
        }
    }

    override fun getMostPopularSeries(){
        coroutineScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getMostPopularSeries(Data.apiKey)
                    .enqueue(object : Callback<MovieResponse> {
                        override fun onResponse(
                            call: Call<MovieResponse>,
                            response: Response<MovieResponse>
                        ) {
                            val body = response.body()!!
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                homeView.onSuccessGetPopularSeries(it)
                                                Log.d("Main Presenter adalah",
                                                    response.body()?.results.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    homeView.onFailureGetPopularSeries(t.message!!)
                                }
                            }
                        }
                    })
            }
        }
    }

    override fun getComingSoon(){
        coroutineScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getComingSoon(
                    Data.apiKey,
                    Data.language,
                    Data.sortBy,
                    Data.releaseDateGte,
                    Data.releaseDateLte
                )
                    .enqueue(object : Callback<ComingSoonResponse> {
                        override fun onResponse(
                            call: Call<ComingSoonResponse>,
                            response: Response<ComingSoonResponse>
                        ) {
                            val body = response.body()!!
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                homeView.onSuccessGetComingSoon(it)
                                                Log.d("Main Presenter adalah",
                                                    response.body()?.results.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<ComingSoonResponse>, t: Throwable) {
                            coroutineScope.launch {
                                withContext(Dispatchers.Main){
                                    homeView.onFailureGetPopularSeries(t.message!!)
                                }
                            }
                        }
                    })
            }
        }
    }
}