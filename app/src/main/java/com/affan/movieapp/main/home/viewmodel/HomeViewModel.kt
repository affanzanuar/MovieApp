package com.affan.movieapp.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.comingsoon.ComingSoon
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.movie.MovieResponse
import com.affan.movieapp.model.series.Series
import com.affan.movieapp.model.trending.Trending
import com.affan.movieapp.model.trending.TrendingResponse
import com.affan.movieapp.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _trending : MutableLiveData<List<Trending?>> = MutableLiveData()
    val trending : LiveData<List<Trending?>> = _trending

    private val _inTheater : MutableLiveData<List<Movie?>> = MutableLiveData()
    val inTheater : LiveData<List<Movie?>> = _inTheater

    private val _popularMovies : MutableLiveData<List<Movie?>> = MutableLiveData()
    val popularMovies : LiveData<List<Movie?>> = _popularMovies

    private val _popularSeries : MutableLiveData<List<Series?>> = MutableLiveData()
    val popularSeries : LiveData<List<Series?>> = _popularSeries

    private val _comingSoon : MutableLiveData<List<ComingSoon?>> = MutableLiveData()
    val comingSoon : LiveData<List<ComingSoon?>> = _comingSoon

    private val _errorMessage : MutableLiveData<String> = MutableLiveData()
    val errorMessage : LiveData<String> = _errorMessage


    fun getTrending(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getTopMoviesOrSeries(Data.apiKey)
                    .enqueue(object : Callback<TrendingResponse> {
                        override fun onResponse(
                            call: Call<TrendingResponse>,
                            response: Response<TrendingResponse>
                        ) {
                            val body = response.body()!!
                            Log.d("Home Presenter body",
                                body.toString())
                            viewModelScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                _trending.value = it
                                                Log.d("Home Presenter adalah",
                                                    it.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main){
                                    _errorMessage.value = t.message
                                }
                            }
                        }
                    })
            }
        }
    }

    fun getInTheater(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                ApiClient.instance.getNowPlaying(Data.apiKey)
                    .enqueue(object : Callback<MovieResponse> {
                        override fun onResponse(
                            call: Call<MovieResponse>,
                            response: Response<MovieResponse>
                        ) {
                            val body = response.body()!!
                            Log.d("Home Presenter body",
                                body.toString())
                            viewModelScope.launch {
                                withContext(Dispatchers.Main){
                                    body.results
                                        .let {
                                            if (it != null) {
                                                _inTheater.value = it
                                                Log.d("Home Presenter adalah",
                                                    it.toString())
                                            }
                                        }
                                }
                            }
                        }

                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                            viewModelScope.launch {
                                withContext(Dispatchers.Main){
                                    _errorMessage.value = t.message
                                }
                            }
                        }
                    })
            }
        }
    }

}